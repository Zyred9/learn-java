package com.example.single;

import com.example.common.server.WebsocketServer;
import com.example.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *          一个账号只能允许同时在线人数为一个
 *          先登录了的会被后登录的挤下去，
 *          使用websocket进行推送登录下线动作的
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
@Service
public class SingleLoginService {

    @Autowired
    private WebsocketServer websocketServer;

    static int initialCapacity = 2;
    static int initialLoginCounter = 0;
    // 模拟数据库
    Map<String, User> database = new ConcurrentHashMap<>(initialCapacity);

    @PostConstruct
    public void initialDatabase () {

        final String password = "admin";

        for (int i = 0; i < initialCapacity ; i++) {
            User user = new User();
            user.setPassword(password);
            user.setUserName("admin" + (i + 1));
            user.setLoginCounter(initialLoginCounter);
            this.database.put(user.getUserName(), user);
        }
    }

    public String login(User user) {
        if (!this.database.containsKey(user.getUserName())) {
            return "404 - User not found.";
        }
        // 第一次登录：跟新到数据库中

        User current = this.database.get(user.getUserName());
        // 如果是同一个用户，同一个sessionId，则只需要提醒不重复点击即可
        if (user.equals(current) && Objects.equals(user.getSessionId(), current.getSessionId())){
            return "Do not click repeat. ";
        }
        postUpdateUser(user, true);
        // 第二次登录，当前的sessionId 肯定和数据库中登录过了的sessionId是不同的
        if(user.equals(current)
                && !Objects.equals(user.getSessionId(), current.getSessionId())
                && Objects.nonNull(current.getSessionId())) {

            websocketServer.send("强制下线", current.getSessionId());
            websocketServer.onClose(websocketServer.getSession(current.getSessionId()));
            // 更新数据库中当前用户的sessionId 为最新的sessionId
            postUpdateUser(user, false);
            return "Account logged in. Force a party that is login`ed off.";
        }
        return String.format("Login success, counter : %d", current.getLoginCounter());
    }

    /**
     * 如果登录当前的用户和存在数据库中的用户
     * 当前登录的人在数据库中没有sessionId的时候，去更新sessionId到数据库中
     * @param current
     */
    private void postUpdateUser(User current, boolean enable) {
        User user = this.database.get(current.getUserName());
        if (Objects.isNull(user.getSessionId()) && enable) {
            user.setSessionId(user.getSessionId());
            this.database.put(user.getUserName(), current);
        } else if (Objects.nonNull(user.getSessionId()) && !enable){
            user.setSessionId(user.getSessionId());
            this.database.put(user.getUserName(), current);
        }
    }

    public void removeUserSession(String sessionId) {
        this.database.entrySet().forEach(
                a -> {
                    if (Objects.equals(a.getValue().getSessionId(), sessionId)
                            && Objects.isNull(sessionId)) {
                        User value = a.getValue();
                        value.setSessionId(null);
                        this.database.put(a.getKey(), value);
                    }
                }
        );
    }
}

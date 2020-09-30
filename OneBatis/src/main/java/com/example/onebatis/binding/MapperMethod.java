package com.example.onebatis.binding;

import com.example.onebatis.builder.SqlBuilder;
import com.example.onebatis.session.Configuration;
import com.example.onebatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class MapperMethod {

    private SqlBuilder sqlBuilder;
    private final MethodSignature method;
    private final Class<?> object;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration, Class object) {
        this.sqlBuilder = configuration.getMappedStatement(mapperInterface.getName().concat(".").concat(method.getName()));
        this.method = new MethodSignature(configuration, mapperInterface, method);
        this.object = object;
    }

    public Object execute(SqlSession sqlSession, Object[] args, SqlBuilder sqlBuilder) {
        Object result = null;
        switch (sqlBuilder.getCommandType()) {
            case INSERT: {
                // TODO
                break;
            }
            case UPDATE: {
                // TODO
                break;
            }
            case DELETE: {
                // TODO
                break;
            }
            case SELECT:
                if (method.returnsVoid()) {
                    // executeWithResultHandler(sqlSession, args);
                    result = null;
                } else if (method.returnsMany()) {
                    result = executeForMany(sqlSession, args, sqlBuilder);
                } else if (method.returnsMap()) {
//                    result = executeForMap(sqlSession, args);
                } else {
                    /*Object param = method.convertArgsToSqlCommandParam(args);
                    // 普通 select 语句的执行入口 >>
                    result = sqlSession.selectOne(command.getName(), param);
                    if (method.returnsOptional()
                            && (result == null || !method.getReturnType().equals(result.getClass()))) {
                        result = Optional.ofNullable(result);
                    }*/
                }
                break;
            case FLUSH:
                result = sqlSession.flushStatements();
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + this.sqlBuilder.getStatementId());
        }
        if (result == null && method.returnType().isPrimitive() && !method.returnsVoid()) {
            throw new RuntimeException("Mapper method '" + this.sqlBuilder.getStatementId()
                    + " attempted to return null from a method with a primitive return type (" + method.returnType() + ").");
        }
        return result;
    }

    private Object executeForMany(SqlSession sqlSession, Object[] args, SqlBuilder sqlBuilder) {
        return sqlSession.selectList(sqlBuilder, args, this.object);
    }


    public static class MethodSignature {

        private final boolean returnsMany;
        private final boolean returnsMap;
        private final boolean returnsVoid;
        private final boolean returnsOptional;
        private final Class<?> returnType;

        public MethodSignature(Configuration configuration, Class<?> mapperInterface, Method method) {

            this.returnType = method.getReturnType();

            this.returnsVoid = void.class.equals(this.returnType);
            this.returnsMany = Collection.class.isAssignableFrom(this.returnType) || this.returnType.isArray();
            this.returnsOptional = Optional.class.equals(this.returnType);
            this.returnsMap = Map.class.equals(this.returnType);
        }

        public boolean returnsMany() {
            return this.returnsMany;
        }

        public boolean returnsMap() {
            return this.returnsMap;
        }

        public boolean returnsVoid() {
            return this.returnsVoid;
        }

        public boolean returnsOptional() {
            return this.returnsOptional;
        }

        public Class<?> returnType() {
            return this.returnType;
        }
    }
}

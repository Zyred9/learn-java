package com.exapmle.spring;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/

/**
 * @ClassName DoubleLinkList
 * @Description TODO
 * @Author issac
 * @Date 2020/10/22 10:58
 */
public class DoubleLinkList {
    private DuLNode head;

    public DoubleLinkList() {
        // 初始化头结点
        head = new DuLNode();
        // 初始化头结点的前驱后驱
        head.setNext(head);
        head.setPrior(head);
    }

    public void insert(int i, Object x) throws Exception {
        DuLNode p = head.getNext();
        int j = 0;
        while (!p.equals(head) && j < i) {
            p = p.getNext();
            ++j;
        }

        if (j != i && !p.equals(head)) {
            throw new Exception("插入位置不合理");
        }

        DuLNode s = new DuLNode(x);
        p.getPrior().setNext(s);
        s.setPrior(p.getPrior());
        s.setNext(p);
        p.setPrior(s);
    }

    public static void main(String[] args) throws Exception {
        DoubleLinkList linkList = new DoubleLinkList();
        linkList.insert(1, 3);

        System.out.println(linkList.toString());
    }
}

class DuLNode {
    private Object data;
    private DuLNode prior;
    private DuLNode next;

    public DuLNode() {
        this(null);
    }

    public DuLNode(Object data) {
        this.data = data;
        this.next = null;
        this.prior = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DuLNode getPrior() {
        return prior;
    }

    public void setPrior(DuLNode prior) {
        this.prior = prior;
    }

    public DuLNode getNext() {
        return next;
    }

    public void setNext(DuLNode next) {
        this.next = next;
    }
}

package behavior.observer;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/30 11:38
 */
public abstract class AbstractPartner {
    public abstract void sendInfo(String info);
}
class Spy1 extends AbstractPartner {
    public void sendInfo(String info) {
        System.out.println("Spy1收到情报："+info);
    }
}
class Spy2 extends AbstractPartner {
    public void sendInfo(String info) {
        System.out.println("Spy2收到情报："+info);
    }
}

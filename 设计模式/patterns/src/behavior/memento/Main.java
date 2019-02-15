package behavior.memento;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/31 16:57
 */
public class Main {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator();
        originator.setState("苹果");
        careTaker.setMemento(originator.createMemento());
        originator.setState("橘子");
        careTaker.setMemento(originator.createMemento());
        originator.setState("香蕉");
        System.out.println("当前状态：" + originator.getState());
        originator.restoreMemento(careTaker.getMemento());
        System.out.println("原始状态：" + originator.getState());
    }
}

package behavior.observer;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/30 12:39
 */
public class Main {
    public static void main(String[] args) {
        AbstractPartner partner1 = new Spy1();
        AbstractPartner partner2 = new Spy2();
        AbstractEnemy american = new AmericanPresident();
        american.addObserver(partner1);
        american.addObserver(partner2);
        american.action();
        AbstractEnemy japan = new JapanPresident();
        japan.addObserver(partner1);
        japan.addObserver(partner2);
        japan.action();
    }
}

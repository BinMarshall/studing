package creational.abstractfactory;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 15:45
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new Factory1();
        AbstractProductA a1 = factory1.createProductA();
        a1.useful();
        AbstractProductB b1 = factory1.createProductB();
        b1.shape();
        AbstractFactory factory2 = new Factory2();
        AbstractProductA a2 = factory2.createProductA();
        a2.useful();
        AbstractProductB b2 = factory2.createProductB();
        b2.shape();
    }
}

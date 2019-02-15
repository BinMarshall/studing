package creational.abstractfactory;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 15:36
 */
public interface AbstractProductA {
    public void useful();
}
class ProductA1 implements AbstractProductA {
    public void useful() {
        System.out.println("A1's useful is eat");
    }
}
class ProductA2 implements  AbstractProductA {
    public void useful() {
        System.out.println("A2's useful is drink");
    }
}

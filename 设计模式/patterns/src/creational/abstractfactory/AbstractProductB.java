package creational.abstractfactory;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 15:38
 */
public interface AbstractProductB {
    public void shape();
}
class ProductB1 implements AbstractProductB {
    public void shape() {
        System.out.println("B1's shape is square");
    }
}
class ProductB2 implements AbstractProductB {
    public void shape() {
        System.out.println("B2's shape is circular");
    }
}

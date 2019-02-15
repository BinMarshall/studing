package creational.builder;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 16:26
 */
public class Main {
    public static void main(String[] args) {
        Director1 director1 = new Director1();
        Director2 director2 = new Director2();
        Product product = director1.createProduct();
        product = director2.createProduct();
    }
}

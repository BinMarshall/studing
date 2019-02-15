package creational.builder;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 16:25
 */
public class Director1 {
    public Product createProduct(){
        AbstractBuilder builder = new ConcreteBuilder();
        Product product = new Product();
        builder.setPart1(product);
        builder.setPart2(product);
        return product;
    }
}

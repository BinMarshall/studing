package creational.builder;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/11 16:37
 */
public class Director2 {
    public Product createProduct(){
        AbstractBuilder builder = new ConcreteBuilder();
        Product product = new Product();
        builder.setPart2(product);
        builder.setPart1(product);
        return product;
    }
}

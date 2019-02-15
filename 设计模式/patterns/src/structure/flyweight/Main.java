package structure.flyweight;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 14:33
 */
public class Main {
    public static void main(String[] args) {
        AbstractFlyweight flyweight = FlyweightFactory.getFlyweight("享元模式");
        InnerBean inner = new InnerBean();
        inner.setId(1L);
        inner.setName("享元");
        flyweight.setInner(inner);
        flyweight.operate();
    }
}

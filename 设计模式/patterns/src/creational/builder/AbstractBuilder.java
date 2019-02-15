package creational.builder;

/**
 * 建造者模式优点：
 * 1、封装性好，客户端不需知道产品内部组成的细节
 * 2、建造者独立，易扩展
 * 3、由于建造者是独立的，因此可以对建造过程逐步细化，
 *    而不对其他模块产生影响
 * 建造者模式缺点：
 * 建造者模式应用场景：
 * 1、当建造方法相同，但执行顺序不同，从而产生不同的事件结果时
 * 2、多个零部件，都可以装配到一个对象中，但产生的运行结果又不相同时
 * 3、产品类很复杂，或者产品类中的调用顺序不同而产生不同的结果
 * 建造者模式最佳实战：
 * 1、建造者模式关注的是零件类型和装配顺序，这是它和工厂模式的最大区别
 */
public interface AbstractBuilder {
    public void setPart1(Product p);
    public void setPart2(Product p);
}
class ConcreteBuilder implements AbstractBuilder {
    public void setPart1(Product p) {
        p.setPart1("组装第1部分");
        System.out.println(p.getPart1());
    }
    public void setPart2(Product p) {
        p.setPart2("组装第2部分");
        System.out.println(p.getPart2());
    }
}

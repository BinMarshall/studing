package creational.factorymethod;

/**
 * 工厂方法模式优点：
 * 1、具有良好的封装性，代码结构清晰
 * 2、工厂方法模式的扩展性非常好
 * 3、屏蔽产品类，客户端不需要关心产品类
 * 4、工厂方法模式是典型的解耦框架，高层模块只需要知道抽象产品类，
 *    不需关心产品实现类。符合迪米特法则、依赖倒置原则、里氏替换原则
 * 工厂方法模式缺点：
 * 工厂方法模式应用场景：
 * 1、工厂方法模式是new一个对象的替代品，在所有声称对象的地方都可用
 */
/**
 * 工厂方法模式和建造者模式的区别
 * 1、意图不同
 *    工厂方法模式关注的是一个产品整体，不关心产品的各个部分是如何创建的，
 *    而建造者模式关注的是一个产品由一个个部件按照顺序组装出来的过程。
 *    工厂方法模式是一个对象创建的粗线条应用，建造者模式则是通过细线条勾勒
 *    出一个复杂对象按步组装的过程。
 * 2、产品复杂度不同
 *    工厂方法模式创建的产品一般都是单一功能产品，而建造者模式创建的则是
 *    一个复合产品，它由各个部件复合而成。
 *    工厂方法模式创建出的对象粒度比较粗；建造者模式创建出的对象粒度比较细。
 */
/**
 * 工厂方法模式和抽象工厂模式的区别
 * 1、工厂方法模式适用于创建从功能上比较独立的产品
 * 2、抽象工厂模式适用于创建一个产品组，特别是产品族中各个产品之间存在关系
 */
public interface AbstractCadillacFactory {
    public <T extends AbstractCadillac> T makeCadillac(Class<T> t);
}
class CadillacFactory implements AbstractCadillacFactory {
    public <T extends AbstractCadillac> T makeCadillac(Class<T> t) {
        T cadillac = null;
        try{
            cadillac = (T)Class.forName(t.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cadillac;
    }
}

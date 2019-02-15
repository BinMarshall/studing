package creational.abstractfactory;

/**
 * 抽象工厂模式优点：
 * 1、封装性好，高层模块不需关心每个产品的实现类，只需知道工厂接口
 * 2、产品组内的约束为非公开状态，如每生产一个女性，就同时生产1.2个男性，
 *    这样的生产过程约束对高层模块是透明的
 * 抽象工厂模式缺点：
 * 1、产品组扩展非常困难，要增加一个新产品，抽象工厂以及具体工厂都要修改
 * 抽象工厂模式应用场景：
 * 1、一个对象族，都有相同的约束，就可以使用该模式
 * 抽象工厂模式最佳实战：
 * 1、产品族中有N个产品，在抽象工厂类中就应该有N个创建方法
 * 2、有M个产品等级就应该有M个实现工厂类
 */
public interface AbstractFactory {
    public AbstractProductA createProductA();
    public AbstractProductB createProductB();
}
class Factory1 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA1();
    }
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
class Factory2 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA2();
    }
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}



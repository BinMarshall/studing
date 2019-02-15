package structure.flyweight;

/**
 * 享元模式：
 * 1、享元模式是实现池技术的重要方式
 * 2、程序中创建太多对象容易内存溢出，享元模式提出了细粒度对象和共享对象，一般细粒
 *    度对象的数量多且性质相近，这些对象的信息可分为两部分：内部状态和外部状态。
 *    内部状态是对象可以共享出来的信息，存储在享元对象内部并且不会随环境改变而改变；
 *    外部状态是对象得以依赖的一个标记，它随环境改变而改变，是不可共享的。
 * 3、享元模式的目的在于运用共享技术，使得一些细粒度对象可以共享。
 * 4、对象池中，一个外部状态只用一个对象表示。对象一旦产生，必然有一个唯一的、
 *    可访问的外部状态标志该对象
 * 享元模式优点：可减少创建对象数量，降低内存使用率
 * 享元模式缺点：提高了复杂度，需要分离内、外部状态，而且外部状态不能随内部状态改变而改变
 * 享元模式应用场景：
 * 1、系统中存在大量相似的对象
 * 2、细粒度对象都具有相似的外部对象，且内部状态与环境无关
 * 3、需要缓冲池的场景
 * 享元模式最佳实战：
 * 1、尽量使用基本类型作为外部状态。如果把对象作为Map的键值，一定要确保重写equals和
 *    hashCode方法，否则会出现通过键值搜索失败的情况
 * 2、享元模式实现的对象池和真正意义上的对象池区别：真正意义上的对象池着重在对象的复用，
 *    从池中获取到A对象还是B对象对于客户端来说完全相同，而享元模式主要解决对象的共享
 */
public abstract class AbstractFlyweight {
    private InnerBean inner; //内部状态
    //final防止外部状态被修改，从而导致池混乱
    protected final String outer; //外部状态
    //要求享元角色必须接受外部状态
    public AbstractFlyweight(String outer){
        this.outer = outer;
    }
    //定义业务操作
    public abstract void operate();
    public InnerBean getInner() { return inner; }
    public void setInner(InnerBean inner) { this.inner = inner; }
}
class ConcreteFlyweight1 extends AbstractFlyweight {
    public ConcreteFlyweight1(String outer) {
        super(outer);
    }
    public void operate() {
        System.out.println("ConcreteFlyweight1:"
                +this.getInner().getId()+";"+this.getInner().getName());
    }
}
class ConcreteFlyweight2 extends AbstractFlyweight {
    public ConcreteFlyweight2(String outer) {
        super(outer);
    }
    public void operate() {
        System.out.println("ConcreteFlyweight2:"
                +this.getInner().getId()+";"+this.getInner().getName());
    }
}

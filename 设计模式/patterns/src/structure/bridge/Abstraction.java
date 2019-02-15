package structure.bridge;

/**
 * 桥梁模式的优点：
 * 1、抽象和实现分离，它是为了解决继承的缺点而提出的
 * 2、扩张能力强，例子中想增加实现没问题，想增加抽象也没问题，
 *    只要对外暴露的接口层允许这样的变化，就已经把变化的可能性
 *    降到最小了
 * 3、让细节对客户透明，客户端不需要关心细节，它已经由抽象层
 *    通过聚合关系完成了封装
 * 桥梁模式的缺点：
 * 桥梁模式的应用场景：
 * 1、不希望或不适合使用继承的场景
 * 2、接口或抽象类不稳定的场景
 * 桥梁模式的最佳实战：
 * 1、桥梁模式是非常简单的，使用该模式时主要考虑如何拆分抽象和
 *    实现，并不是一涉及继承就要使用该模式
 * 2、桥梁模式的意图是对变化的封装，尽量把可能变化的因素封装到
 *    最细、最小的逻辑单元中，避免风险扩散。因此在设计时，当类
 *    的继承有N多层时，可以考虑使用桥梁模式
 */
public abstract class Abstraction {
    private Implementor implementor;
    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }
    public void request(){
        this.implementor.doSomeThing();
    }
    public Implementor getImplementor(){
        return this.implementor;
    }
}
class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }
    public void request(){
        super.request();
        super.getImplementor().doAnyThing();
    }
}

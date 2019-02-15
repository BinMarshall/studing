package behavior.observer;

import java.util.Vector;

/**
 * 观察者模式优点：
 * 1、观察者和被观察者之间被抽象耦合
 * 2、建立了一套触发机制
 * 观察者模式缺点：一个被观察者，多个观察者情况下需要考虑效率问题
 * 观察者模式应用场景：
 * 1、关联行为场景，注意：关联行为是可拆分的
 * 2、事件多级触发场景
 * 3、跨系统的消息交换场景，如消息队列
 * 观察者模式最佳实践：
 * 1、观察者和被观察者之间消息沟通：观察者中的方法一般接收两个参数，一个是被观察者，
 *    一个是DTO，DTO由被观察者生成，由观察者消费
 * 2、观察者响应方式：观察者是个复杂逻辑体，除了接收被观察者传过来的信息，还要处理
 *    其他逻辑，若果一个观察者对应多个被观察者，观察者可能来不及快速响应。解决方法：
 *    ①采用多线程，通过异步方式处理；②采用缓存技术，通过同步方式处理
 * 3、被观察者尽量自己做主：尽量增加一个action(boolean isNotify)的方法，决定当被
 *    观察者采取行动时是否通知观察者
 */
/** 间谍监听 */
public abstract class AbstractEnemy {
    private Vector<AbstractPartner> partners = new Vector<>();
    public void addObserver(AbstractPartner partner){
        this.partners.add(partner);
    }
    public void notifyObservers(String info){
        partners.stream().peek(one -> one.sendInfo(info)).count();
    }
    public abstract void action();
}
class AmericanPresident extends AbstractEnemy{
    public void action() {
        this.notifyObservers("美国要偷袭中国");
    }
}
class JapanPresident extends AbstractEnemy {
    public void action() {
        this.notifyObservers("日本要攻击中国");
    }
}

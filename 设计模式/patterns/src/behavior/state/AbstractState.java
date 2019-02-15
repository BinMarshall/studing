package behavior.state;

/**
 * 状态模式的优点：
 * 1、避免多重条件判断；
 * 2、很好的体现了开闭原则和单一职责原则；
 * 3、封装性很好，状态变更放到类内部实现，
 *    外部调用不需知道类内部如何实现状态和行为的变换
 * 状态模式的缺点：如果状态太多，会导致子类太多，不好管理。
 *    但解决方式很多，如在数据库中设置一张状态表，根据状态之星相应的操作
 * 状态模式应用场景：
 * 1、行为随状态改变而改变
 * 2、条件、分支语句的替代者
 */
/** 把大象装冰箱总共分三步 */
public abstract class AbstractState {
    private Context context = null;
    public void setContext(Context context){
        this.context = context;
    }
    public Context getContext(){
        return this.context;
    }
    public abstract void execute();
}
class OpenDoorState extends AbstractState {
    public void execute() {
        System.out.println("把冰箱门打开");
        this.getContext().changeState(Context.PUTIN);
    }
}
class PutElephentInRefrigeratorState extends AbstractState {
    public void execute() {
        System.out.println("把大象装冰箱里");
        this.getContext().changeState(Context.CLOSE);
    }
}
class CloseDoorState extends AbstractState {
    public void execute() {
        System.out.println("把门带上");
    }
}

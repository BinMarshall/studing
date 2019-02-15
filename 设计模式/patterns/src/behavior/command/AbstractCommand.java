package behavior.command;

/**
 * 命令模式优点：
 * 1、实现调用者和接受者之间解耦
 * 2、扩展性好，添加新命令非常容易
 * 3、与其他模式结合更优秀：结合责任链模式，实现命令族执行命令；
 *    结合模板方法模式，可减少命令子类的膨胀问题
 * 命令模式缺点：随着命令增多，命令类也会增多，导致类膨胀
 * 命令模式应用场景：只要认为是命令的地方就可以以使用该模式
 * 命令模式最佳实践：命令模式的Receiver在实际应用中一般会被命令
 *    封装掉，从而减少高层模块对Receiver的直接依赖
 */
public interface AbstractCommand {
    public abstract void transfer();
}
class WashingCommand implements AbstractCommand {
    public void transfer() {
        Receiver husband = new Husband();
        husband.execute();
    }
}
class CleanFloorCommand implements AbstractCommand {
    public void transfer() {
        Receiver son = new Son();
        son.execute();
    }
}
class CleanDeskCommand implements AbstractCommand {
    public void transfer() {
        Receiver daughter = new Daughter();
        daughter.execute();
    }
}

package behavior.command;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 17:17
 */
public class Invoker {
    private AbstractCommand command;
    public Invoker(AbstractCommand command){
        this.command = command;
    }
    public void invoke(){
        this.command.transfer();
    }
}

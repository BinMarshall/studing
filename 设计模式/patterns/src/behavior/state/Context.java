package behavior.state;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 15:37
 */
public class Context {
    public static AbstractState OPEN = new OpenDoorState();
    public static AbstractState PUTIN = new PutElephentInRefrigeratorState();
    public static AbstractState CLOSE = new CloseDoorState();
    private AbstractState state;
    public void changeState(AbstractState state){
        this.state = state;
        this.state.setContext(this);
    }
    public void execute(){
        this.state.execute();
    }
}

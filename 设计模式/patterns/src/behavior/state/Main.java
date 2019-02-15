package behavior.state;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 15:51
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        AbstractState state = new OpenDoorState();
        context.changeState(state);
        context.execute();
        context.execute();
        context.execute();
    }
}

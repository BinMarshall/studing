package behavior.command;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 17:22
 */
public class CommandFactory {
    private String type;
    public CommandFactory(String type){
        this.type = type;
    }
    public AbstractCommand createCommand(){
        if("one".equals(type)){
            return new WashingCommand();
        }else if("two".equals(type)){
            return new CleanFloorCommand();
        }else{
            return new CleanDeskCommand();
        }
    }
}

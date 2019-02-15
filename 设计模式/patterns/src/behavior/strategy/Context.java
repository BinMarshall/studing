package behavior.strategy;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 15:14
 */
public class Context {
    private AbstractStrategy strategy;
    public void setStrategy(AbstractStrategy strategy){
        this.strategy = strategy;
    }
    public void carryOut(){
        this.strategy.attack();
    }
}

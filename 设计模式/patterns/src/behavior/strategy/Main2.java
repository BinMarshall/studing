package behavior.strategy;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 16:27
 */
public class Main2 {
    public static void main(String[] args) {
        Context context = new Context();
        StrategyFactory factory = new StrategyFactory(args[0]);
        context.setStrategy(factory.createStrategy());
        context.carryOut();
    }
}
class StrategyFactory{
    private String type;
    public StrategyFactory(String type){
        this.type = type;
    }
    public AbstractStrategy createStrategy(){
        if("one".equals(this.type)){
            return new StraightStrategy();
        }else if("two".equals(this.type)){
            return new SecretlyStrategy();
        }else if("three".equals(this.type)){
            return new StarveStrategy();
        }else{
            return new StraightStrategy();
        }
    }
}

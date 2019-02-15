package behavior.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/1/29 15:17
 */
public class Main1 {
    public static void main(String[] args) {
        Map<String, AbstractStrategy> strategys = new HashMap<>();
        strategys.put("one", new StraightStrategy());
        strategys.put("two", new SecretlyStrategy());
        strategys.put("three", new StarveStrategy());
        Context context = new Context();
        context.setStrategy(strategys.get(args[0]));
        context.carryOut();
    }
}

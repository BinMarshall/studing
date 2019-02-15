package structure.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2019/2/1 14:27
 */
public class FlyweightFactory {
    private static Map<String, AbstractFlyweight> pool = new HashMap<>();
    public static AbstractFlyweight getFlyweight(String outer){
        AbstractFlyweight flyweight = null;
        if(pool.containsKey(outer)){
            flyweight = pool.get(outer);
        }else{
            Random random = new Random();
            flyweight = random.nextInt(2) == 0 ? new ConcreteFlyweight1(outer)
                    : new ConcreteFlyweight2(outer);
            pool.put(outer, flyweight);
        }
        return flyweight;
    }
}

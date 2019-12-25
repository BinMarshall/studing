package cn.bravolinks.erp.crm.server;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/10/29 17:12
 */
public class JedisTest {
    public static void main(String[] args) {
        RedisPool redis = new RedisPool();
        redis.execute(jedis -> jedis.set("",""));
    }
}
interface CallWithJedis { public void call(Jedis jedis); }
class RedisPool {
    private JedisPool pool;
    public RedisPool() {
        this.pool = new JedisPool();
    }
    public void execute(CallWithJedis caller) {
        try (Jedis jedis = pool.getResource()) {
            caller.call(jedis);
        }catch(Exception e){
            try(Jedis jedis = pool.getResource()){
                caller.call(jedis);
            }catch(Exception e1){
                try(Jedis jedis = pool.getResource()){
                    caller.call(jedis);
                }
            }
        }
    }
}
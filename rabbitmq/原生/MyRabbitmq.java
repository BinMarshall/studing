import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2018/10/2 11:26
 */
public class MyRabbitmq {
    private final static String EXCHANGE_NAME = "direct_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.108");
        factory.setUsername("marshal");
        factory.setPassword("password");
        //factory.setPort(5672);
        Connection con = factory.newConnection();  //创建连接
        Channel channel = con.createChannel();     //创建信道
//        channel.confirmSelect();                    //将信道开启发送方确认模式
//        channel.txSelect();                         //将信道开启事务模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);  //创建交换器
        String[] loglevels = {"error", "info", "warn"};
        for(int i=0; i<loglevels.length; i++){
             String level = loglevels[i];   //定义路由键
             String message = "Hello " + (i+1);
             //发送消息：交换器名、路由键、基本参数、消息内容
             channel.basicPublish(EXCHANGE_NAME, level, null, message.getBytes());
             System.out.println("Sent " + level + ":" + message);
        }
        channel.close();
        con.close();
    }
}

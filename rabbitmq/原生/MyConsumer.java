import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHALL
 * 创建时间：2018/10/2 12:13
 */
public class MyConsumer {
    public static final String EXCHANGE_NAME = "direct_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.108");
        factory.setUsername("marshal");
        factory.setPassword("password");
        Connection con = factory.newConnection();
        Channel channel = con.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue(); //声明一个随机队列
        String[] loglevels = {"error", "info", "warn"};
        for(String level : loglevels){
            channel.queueBind(queueName, EXCHANGE_NAME, level);
        }
        System.out.println("Waiting message......");

        Consumer consumerA = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("Accept:"+envelope.getRoutingKey()+":"+message);
            }
        };
        System.out.println("-------------");
        String res = channel.basicConsume(queueName, true, consumerA);
        System.out.println("------------:"+res);
    }
}

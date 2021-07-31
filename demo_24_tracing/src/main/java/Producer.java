import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("queue.tc.demo", false, false, false, null);
        channel.exchangeDeclare("ex.tc.demo", "direct", false);
        channel.queueBind("queue.tc.demo", "ex.tc.demo", "key.tc");

        for (int i = 0; i < 100; i++) {
            channel.basicPublish("ex.tc.demo", "key.tc", null, ("hello" + i).getBytes());
        }

        channel.close();
        connection.close();
    }
}

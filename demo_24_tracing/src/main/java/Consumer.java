import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://root:123456@192.168.192.129:5672/%2f");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("queue.tc.demo", false, false, false, null);

        for (int i = 0; i < 25; i++) {
            final GetResponse getResponse = channel.basicGet("queue.tc.demo", true);
            System.out.println(new String(getResponse.getBody()));
        }

        channel.close();
        connection.close();
    }
}

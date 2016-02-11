import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by kurunsk on 11-02-2016.
 */
public class MyJMSMessageConsumer implements ExceptionListener{
    public static void main(String[] args) {

        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            Connection connection = null;

            connection = connectionFactory.createConnection();

            connection.start();
            //connection.setExceptionListener(this);

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("TEST.SANAL");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();
            if(message instanceof TextMessage){
                TextMessage msg = (TextMessage) message;
                System.out.println(msg.getText());
            }else{
                System.out.println("Received " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onException(JMSException e) {

    }
}

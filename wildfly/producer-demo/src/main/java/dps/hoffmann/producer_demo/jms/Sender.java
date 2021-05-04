package dps.hoffmann.producer_demo.jms;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.io.Serializable;

@SessionScoped
@Slf4j
public class Sender implements Serializable {

    private static final String BROKER_URL = "tcp://host.docker.internal:61616";
    private static final String QUEUE_NAME = "test-queue";

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public Sender() {

        try {

            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(BROKER_URL);

            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);

            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (JMSException e) {
            log.error("Sender constructor error: " + e);
        }

    }

    public boolean createTask(String content) {
        try {
            Message m = session.createTextMessage(content);
            producer.send(m);
            return true;
        } catch (JMSException e) {
            log.error("Sender createTask method error", e);
            return false;
        }
    }

    public boolean createTask(Serializable content) {
        try {
            Message m = session.createObjectMessage(content);
            producer.send(m);
            return true;
        } catch (JMSException e) {
            log.error("Sender createTask method error", e);
            return false;
        }
    }

    @PreDestroy
    public void closeConnection() {
        log.info("closing connection / session");
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.error("error closing session / connection");
        }
    }


}
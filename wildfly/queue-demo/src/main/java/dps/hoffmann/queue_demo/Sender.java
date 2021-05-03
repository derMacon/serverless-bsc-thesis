package dps.hoffmann.queue_demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.jms.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

@SessionScoped
@Slf4j
public class Sender implements Serializable {

    private static final String BROKER_URL = "tcp://host.docker.internal:61616";
    private static final String QUEUE_NAME = "test-queue";

    private SecureRandom random = new SecureRandom();

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

    public void createTask(String content){
        String taskName = generateTaskName();
        try {
            Message m = session.createObjectMessage(new Payload(content));
            producer.send(m);
        } catch (JMSException e) {
            log.error("Sender createTask method error", e);
        }
    }

    private String generateTaskName() {
        return new BigInteger(20, random).toString(16);
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
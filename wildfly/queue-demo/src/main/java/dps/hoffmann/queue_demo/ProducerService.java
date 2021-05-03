package dps.hoffmann.queue_demo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProducerService {

    @Inject
    private Sender sender;

    public void sendTextMessage(String content) {
        sender.createTask(content);
    }

    public void sendXmlMessage(String content) {

    }



}

package dps.hoffmann.queue_demo;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/producer")
@Slf4j
public class RestProducer {

    @Inject
    private Sender sender;

    @GET
    @Produces("text/plain")
    @Path("/send/{message}")
    public String hello(@PathParam("message") String content) {
        log.info("mesage content: " +  content);
        sender.createTask(content);
        return content;
    }

}
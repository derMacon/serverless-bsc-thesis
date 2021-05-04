package dps.hoffmann.queue_demo.controller;

import dps.hoffmann.queue_demo.service.ProducerService;
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
    private ProducerService producerService;

    @GET
    @Produces("text/plain")
    @Path("/send-text/{message}")
    public String send(@PathParam("message") String content) {
        log.info("mesage content: " +  content);
        producerService.sendTextMessage(content);
        return content;
    }

    @GET
    @Produces("text/plain")
    @Path("/send-xml-sample")
    public String sampleXml() {
        return producerService.sendSampleXml() + "";
    }

}
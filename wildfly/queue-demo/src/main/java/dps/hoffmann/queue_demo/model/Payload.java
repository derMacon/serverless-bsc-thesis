package dps.hoffmann.queue_demo.model;

import java.io.Serializable;

public class Payload implements Serializable {

    private final String payload;

    public Payload(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}

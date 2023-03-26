package htl.leonding;


import htl.leonding.outgoing.MqttPublischer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/resource")
public class GreetingResource {

    @Inject
    MqttPublischer mqttPublischer;

    @GET
    @Path("/send/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String send(@PathParam("value") String value) {
        mqttPublischer.send(value);
        return "Message sent!";
    }

    @GET
    @Path("/sendToRasa/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendToRasa(@PathParam("value") String value) {
        mqttPublischer.sendToRasa(value);
        return "Message sent to Rasa!";
    }
}
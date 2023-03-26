package htl.leonding;


import htl.leonding.outgoing.MqttPublischer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/send")
public class GreetingResource {

    @Inject
    MqttPublischer mqttPublischer;

    @GET
    @Path("{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("value") String value) {
        mqttPublischer.send(value);
        return "Pressure sent";
    }
}
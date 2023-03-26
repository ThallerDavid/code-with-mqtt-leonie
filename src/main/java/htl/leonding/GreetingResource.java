package htl.leonding;


import htl.leonding.outgoing.MqttPublischer;

import javax.inject.Inject;
import javax.ws.rs.*;
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
        return "Message sent! " + value;
    }

    @POST
    @Path("/send/{value}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPost(@PathParam("value") String value) {
        return value;
    }

}
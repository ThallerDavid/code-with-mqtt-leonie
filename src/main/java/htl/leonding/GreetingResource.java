package htl.leonding;


import htl.entity.Message;
import htl.leonding.outgoing.MqttPublischer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/resource")
public class GreetingResource {

    private static List<String> messages = new ArrayList<String>();

    @Inject
    MqttPublischer mqttPublischer;

    /*
    @GET
    @Path("/send/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public String send(@PathParam("value") Message message) {
        mqttPublischer.send(message);
        return "Message sent! " + message.getText();
    }*/


    @GET
    @Path("/get/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getMessages() {
        return messages;
    }


    @POST
    @Transactional
    @Path("/send/rasa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPost(Message message) {
        messages.add(message.getText());
        return "Message posted! " + message.getSender() + ": " + message.getText();
    }

}
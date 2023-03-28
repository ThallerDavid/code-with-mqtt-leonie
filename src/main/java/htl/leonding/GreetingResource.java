package htl.leonding;


import htl.entity.Message;
import htl.leonding.outgoing.MqttPublischer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

@Path("/resource")
//@Path("/d.pavelescu")
public class GreetingResource {

    private static List<String> messages = new ArrayList<String>();

    @Inject
    MqttPublischer mqttPublischer;

    @Inject
    Logger LOG;

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


   /* @POST
    @Transactional
    @Path("/send/rasa")
    //@Path("/rasa/webhooks/web/webhook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPost(Message message) {
        messages.add(message.getText());
        mqttPublischer.send(message);
        LOG.info("Message posted! " + message.getSender() + ": " + message.getText());
        return "Message posted! " + message.getSender() + ": " + message.getText();
    }*/


    @POST
    @Transactional
    @Path("/send/rasa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPostToRasa(Message message) {
        messages.add(message.getText());
        mqttPublischer.send(message);
        LOG.info("Message posted! " + message.getSender() + ": " + message.getText());

        try {
            String url = "https://student.cloud.htl-leonding.ac.at/d.pavelescu/rasa/webhooks/web/webhook";
            HttpClient client = HttpClients.createDefault();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
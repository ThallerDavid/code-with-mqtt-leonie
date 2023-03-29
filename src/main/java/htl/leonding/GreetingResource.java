package htl.leonding;


import htl.entity.Message;
import htl.leonding.outgoing.MqttPublischer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.List;


@Path("/resource")
//@Path("/d.pavelescu")
public class GreetingResource {

    private static List<String> messages = new ArrayList<String>();

    @Inject
    MqttPublischer mqttPublischer;

    @Inject
    Logger LOG;

    @GET
    @Path("/get/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getMessages() {
        return messages;
    }


    @POST
    @Transactional
    //@Path("/send/rasa")
    @Path("/send/rasa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String sendPostToRasa(Message message) {
        //messages.add(message.getText());
        mqttPublischer.send(message);
        LOG.info("Message posted! " + message.getSender() + ": " + message.getText());

        try {
            String url = "https://student.cloud.htl-leonding.ac.at/d.pavelescu/rasa/webhooks/web/webhook";
            HttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            String json = "{\"sender\": \"" + message.getSender() + "\", \"text\": \"" + message.getText() + "\"}";
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

            httpPost.setEntity(entity);
            HttpResponse response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);
            //JSONObject jo = new JSONArray(responseString).getJSONObject(1);
            //String senderId = jo.getString("recipient_id");
            //String text = jo.getJSONObject("").getString("text");

            JSONArray jsonArray = new JSONArray(responseString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String text = jsonObject.getString("text");
            String senderId = jsonObject.getString("recipient_id");
            System.out.println("Sender Id: " + senderId);
            System.out.println("Sender Id: " + senderId);
            System.out.println("Text: " + text);

            message.setSender(senderId);
            message.setText(text);
            mqttPublischer.sendToRasa(message);

            //System.out.println("output: " + responseString);
            //Message message1 = new Message(responseString);
            //mqttPublischer.send(Message(responseString));
            return responseString;
        } catch (Exception e) {
            LOG.error("Failed to send message to Rasa webhook", e);
            return "Failed to send message to Rasa webhook";
        }
    }
}
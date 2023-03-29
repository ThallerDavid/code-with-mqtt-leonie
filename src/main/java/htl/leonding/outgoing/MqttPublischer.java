package htl.leonding.outgoing;

import htl.entity.Message;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

public class MqttPublischer {

    @Inject @Channel("outgoing-channel")
    Emitter<String> emitter;


    //payload = Nutzlast
    public void send(Message payload) {
        String topic = "itp/users/"+ payload.getSender() +"/question";

        MqttMessage mqttMessage = MqttMessage.of(topic, payload.getText());
        emitter.send(mqttMessage);
        sendToRasa(payload);
    }

    public void sendToRasa(Message payload) {
        String topic = "itp/leonie/"+ payload.getSender() +"/answer";

        MqttMessage mqttMessage = MqttMessage.of(topic, payload.getText());
        emitter.send(mqttMessage);
    }



}

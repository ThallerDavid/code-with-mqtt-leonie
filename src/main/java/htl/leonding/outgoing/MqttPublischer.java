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
    public void send(Message message) {
        String topic = "itp/leonie/message";

        MqttMessage mqttMessage = MqttMessage.of(topic, message.getText());
        emitter.send(mqttMessage);
        sendToRasa(mqttMessage);
    }
    public void sendToRasa(MqttMessage message) {
        String topic = "itp/rasa/message";
        emitter.send(String.valueOf(MqttMessage.of(topic, message.getPayload())));
    }


}

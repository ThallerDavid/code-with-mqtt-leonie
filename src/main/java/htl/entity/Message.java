package htl.entity;

public class Message {
    String sender;
    String text;

    Object metadata;

    public Message(String sender, String text, Object metadata) {
        this.sender = sender;
        this.text = text;
        this.metadata = metadata;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

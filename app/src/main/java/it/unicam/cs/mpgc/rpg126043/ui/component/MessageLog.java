package it.unicam.cs.mpgc.rpg126043.ui.component;

import java.util.ArrayList;
import java.util.List;
/** List of screen messages with limited duration. */
public class MessageLog {
    private static final int LIFETIME_FRAMES=120;
    private final List<Message> messages = new ArrayList<>();


    public void addMessage(String text){
        messages.add(new Message(text));
    }
    public void clear(){
        messages.clear();
    }

    public void tick(){
        messages.forEach(Message::tick);
        messages.removeIf(message->message.isExpired(LIFETIME_FRAMES));
    }

    public List<String> texts(){
        return messages.stream().map(Message::getText).toList();
    }


}

package nosql.anew.chatbox.interfaces;

import java.util.ArrayList;

import nosql.anew.chatbox.models.ChatPojo;



public interface ModelCallBacks {
    void onModelUpdated(ArrayList<ChatPojo> messages);
}

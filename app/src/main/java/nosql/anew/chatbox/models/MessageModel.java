package nosql.anew.chatbox.models;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import nosql.anew.chatbox.interfaces.ModelCallBacks;


public class MessageModel {
    private ArrayList<ChatPojo> mMessages;

    public void addMessages(DataSnapshot dataSnapshot, ModelCallBacks callBacks){
        if (mMessages==null){
            mMessages= new ArrayList<>();
        }
        ChatPojo chatPojo=new ChatPojo(dataSnapshot);
        mMessages.add(chatPojo);
        callBacks.onModelUpdated(mMessages);
    }
}

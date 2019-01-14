package nosql.anew.chatbox.viewModels;

import android.databinding.BaseObservable;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import nosql.anew.chatbox.models.ChatPojo;
import nosql.anew.chatbox.models.MessageModel;
import nosql.anew.chatbox.interfaces.FirebaseCallBacks;
import nosql.anew.chatbox.interfaces.ModelCallBacks;
import nosql.anew.chatbox.interfaces.Observer;
import nosql.anew.chatbox.utils.FirebaseManager;
import nosql.anew.chatbox.utils.MyUtils;

public class ChatViewModel extends BaseObservable implements FirebaseCallBacks,ModelCallBacks {

    private String mRoomName;
    private MessageModel mModel;
    public ArrayList<Observer> observers;


    public ChatViewModel(String roomName) {
        this.mRoomName=roomName;
        mModel=new MessageModel();
        observers=new ArrayList<>();
    }


    public void sendMessageToFirebase(String message) {
        if (!message.trim().equals("")){
            FirebaseManager.getInstance(mRoomName,this).sendMessageToFirebase(message);
        }
    }

    public void setListener() {
        FirebaseManager.getInstance(mRoomName,this).addMessageListeners();
    }

    public void onDestory() {
        FirebaseManager.getInstance(mRoomName,this).removeListeners();
        FirebaseManager.getInstance(mRoomName,this).destroy();
    }

    @Override
    public void onNewMessage(DataSnapshot dataSnapshot) {
        mModel.addMessages(dataSnapshot,this);
    }

    @Override
    public void onModelUpdated(ArrayList<ChatPojo> messages) {
        if (messages.size()>0) {
            notifyObservers(MyUtils.UPDATE_MESSAGES,messages);
        }
    }

    public void addObserver(Observer client) {
        if (!observers.contains(client)) {
            observers.add(client);
        }
    }

    public void removeObserver(Observer clientToRemove) {
        if (observers.contains(clientToRemove)) {
            observers.remove(clientToRemove);
        }
    }

    public void notifyObservers(int eventType, ArrayList<ChatPojo> messages) {
        for (int i=0; i< observers.size(); i++) {
            observers.get(i).onObserve(eventType, messages);
        }
    }
}

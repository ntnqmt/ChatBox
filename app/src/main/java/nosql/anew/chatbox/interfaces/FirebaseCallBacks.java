package nosql.anew.chatbox.interfaces;

import com.google.firebase.database.DataSnapshot;


public interface FirebaseCallBacks {
    void onNewMessage(DataSnapshot dataSnapshot);
}

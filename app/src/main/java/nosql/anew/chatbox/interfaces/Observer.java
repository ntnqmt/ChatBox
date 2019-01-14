package nosql.anew.chatbox.interfaces;


public interface Observer<T> {
    void onObserve(int event, T eventMessage);
}

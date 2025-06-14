public interface Observable {
    void add(Observer obj);

    void remove(Observer obj);

    void notifySubscribers(String msg);

    // We will use java generics as data can be Timestamp (example event) or Integer (Example stock)
    <T> void setData(T data);

    <T> T getData();
}

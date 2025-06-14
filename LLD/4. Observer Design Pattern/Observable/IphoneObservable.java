import java.util.ArrayList;
import java.util.List;

public class IphoneObservable implements Observable {

    Integer availableStock = 0;
    List<Observer> observerList = new ArrayList<>();

    @Override
    public void add(Observer obj) {
        observerList.add(obj);
    }

    @Override
    public void remove(Observer obj) {
        observerList.remove(obj);
    }

    @Override
    public void notifySubscribers(String msg) {
        for (Observer o : observerList) {
            o.update(msg);
        }
    }

    @Override
    public <T> void setData(T data) {
        availableStock = availableStock + (Integer) data;
        if(availableStock - (Integer) data == 0){
            // Only notify when stock was empty and now is available
            final String msg = "Iphone's are back in stock!!! Hurry only " + availableStock + " left";
            notifySubscribers(msg);
        }
    }

    @Override
    public <T> T getData() {
        return (T) availableStock;
    }
}

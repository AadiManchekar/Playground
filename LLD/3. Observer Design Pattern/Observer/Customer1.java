package Observer;

import Observerable.IPhoneObservable;
import Observerable.Subject;

public class Customer1 implements Observer {

    private int value;
	private Subject subject;

    public Customer1(Subject subject) {
        this.subject = subject;
        // Register this observer
        subject.registerObserver(this);
    }

    @Override
    public void notifyObserver() {
        // Observer fetches the relevant data
        if(subject instanceof IPhoneObservable) {
            IPhoneObservable iPhoneObservable = (IPhoneObservable) subject;
            value = iPhoneObservable.getQuantity();
            // So in this way we got the state of subject
        }   
    }
    
}

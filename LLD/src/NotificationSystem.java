/*============================
      Notification & Decorators
=============================*/

import java.util.ArrayList;
import java.util.List;

interface INotification{
    String getContent();
}

// Concrete Notification: simple text notification.
class SimpleNotification implements INotification{
    String text;

    public SimpleNotification(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }
}

// make abstract decorator (decorator pattern contain is a and has a both relationship)

abstract class INotificationDecorator implements INotification { // is a relationship
    INotification notification; // has a relationship

    public INotificationDecorator(INotification notification) {
        this.notification = notification;
    }
}

class TimeStampDecorator extends INotificationDecorator{
    private String timeStamp;

    public TimeStampDecorator(INotification notification, String timeStamp) {
        super(notification);
        this.timeStamp = timeStamp;
    }

    @Override
    public String getContent() {
        return notification.getContent() + timeStamp;
    }
}

class SignDecorator extends INotificationDecorator{
    private String sign;

    public SignDecorator(INotification notification, String sign) {
        super(notification);
        this.sign = sign;
    }

    @Override
    public String getContent() {
        return notification.getContent() + sign;
    }
}

/*============================
  Observer Pattern Components
=============================*/
// Observer interface: each observer gets an update with a Notification pointer.
interface IObserver{
    void update();
}

interface IObservable{
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObserver();
}

class NotificationObservable implements IObservable{
    List<IObserver> observers = new ArrayList<>();
    private INotification currentNotification = null;

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(IObserver observer : observers){
            observer.update();
        }
    }

    public void setNotification(INotification notification) {
        this.currentNotification = notification;
        notifyObserver();
    }

    public INotification getNotification() {
        return currentNotification;
    }

    public String getNotificationContent(){
        return currentNotification.getContent();
    }


}

/*============================
       ConcreteObservers
=============================*/

class Logger implements IObserver {
    private NotificationObservable notificationObservable;

    public Logger() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public Logger(NotificationObservable notificationObservable) {
        notificationObservable.addObserver(this);
        this.notificationObservable = notificationObservable;
    }

    @Override
    public void update() {
        System.out.println("logging new notification " + notificationObservable.getNotificationContent());
    }
}

interface INotificationStrategy {
    void sendNotification(String content);
}

class EmailStrategy implements INotificationStrategy{
    private String emailId;

    public EmailStrategy(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("sending email to emailId and content " + this.emailId + content );
    }
}

class SmsStrategy implements INotificationStrategy{
    private String mobile;

    public SmsStrategy(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("sending sms to mobileno and content " + this.mobile + content );
    }
}

class PopUpStrategy implements INotificationStrategy{

    public PopUpStrategy() {
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("sending popup content " + content );
    }
}

class NotificationEngine implements IObserver{
    NotificationObservable notificationObservable;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        this.notificationObservable.addObserver(this);
    }

    public NotificationEngine(NotificationObservable notificationObservable) {
        this.notificationObservable = notificationObservable;
    }

    public void addNotificationStrategy(INotificationStrategy ns) {
        this.notificationStrategies.add(ns);
    }

    @Override
    public void update() {

        String notificationContent = notificationObservable.getNotificationContent();
        for (INotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationContent);
        }

    }
}


public class NotificationSystem {
    public static void main(String[] args) {

        // create Notification Service

        /*=========================
        since we need only one object of notification service so make it Singleton Class
        =========================  */      
        NotificationService notificationService = NotificationService.getInstance();

        // create Observer
        Logger logger = new Logger();
        NotificationEngine notificationEngine = new NotificationEngine();

        notificationEngine.addNotificationStrategy(new EmailStrategy("test@gmail.com"));
        notificationEngine.addNotificationStrategy(new SmsStrategy("1234567890"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());

        INotification notification1 = new SimpleNotification(" notification processing has started ");
        notification1 = new TimeStampDecorator(notification1, " time added ");
        notification1 = new SignDecorator(notification1, " sign added ");

        INotification notification2 = new SimpleNotification("notification processing has started ");
        notification2 = new SignDecorator(notification2, "sign added ");
        notification2 = new TimeStampDecorator(notification2, "time added ");

        notificationService.sendNotification(notification1);
        //notificationService.sendNotification(notification2);

    }
}

/*=========================
       since we need only one object of notification service so make it Singleton Class
=========================*/
class NotificationService{
    List<INotification> notificationList = new ArrayList<>();
    private NotificationObservable notificationObservable;
    private static NotificationService instance = null;

    private NotificationService() {
        notificationObservable = new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(instance == null){
            instance = new NotificationService();
        }
        return instance;
    }

    public void sendNotification(INotification notification) {
        notificationList.add(notification);
        notificationObservable.setNotification(notification);
    }

    public NotificationObservable getObservable() {
        return notificationObservable;
    }
}

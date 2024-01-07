package systemDesign.LLD.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    public Integer id;
    public String userId;
    public String message;
}

class EmailNotification extends Notification { // singleton
    private static EmailNotification instance;
    private EmailNotification() {
        super();
    }
    public static EmailNotification getInstance() {
        if (instance == null) {
            synchronized (EmailNotification.class){
                if (instance == null) {
                    instance = new EmailNotification();
                }
            }
        }
        return instance;
    }
}

class SMSNotification extends Notification {
    String phoneNumber;
}

class PushNotification extends Notification {
    String deviceId;
}


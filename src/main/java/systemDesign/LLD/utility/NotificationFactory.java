package systemDesign.LLD.utility;

public class NotificationFactory {
    public static Notification getNotification(NotificationType type) {
        return switch (type) {
            case EMAIL -> EmailNotification.getInstance();
            case SMS -> new SMSNotification();
            case PUSH -> new PushNotification();
            default -> null;
        };
    }
}

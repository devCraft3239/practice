package systemDesign.LLD.utility;

public class PaymentFactory {
    public static Payment getPayment(PaymentType type) {
        return switch (type) {
            case CREDIT_CARD -> CreditCard.getInstance();
            case UPI -> new UPI();
            case CASH -> new Cash();
            default -> null;
        };
    }
}

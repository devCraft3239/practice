package systemDesign.LLD.utility;

import lombok.Builder;

import java.util.List;

@Builder
public class Payment {
    public String id;
    public String userId;
    public double amount;
    public PaymentStatus status;
    public PaymentType type;
    List<BillingItem> billingItems;
}

class PaymentStrategy {
    void pay(Payment payment) {
        // pay
    }
}

class CreditCard extends PaymentStrategy { // singleton
    String cardNumber;
    String cvv;
    String expiryDate;

    private static CreditCard instance;
    private CreditCard() {}
    public static CreditCard getInstance() {
        if (instance == null) {
            synchronized (CreditCard.class){
                if (instance == null) {
                    instance = new CreditCard(); // lazy initialization
                }
            }
        }
        return instance;
    }

    @Override
    void pay(Payment payment) {
        // pay
    }
}

class UPI extends PaymentStrategy {
    String upiId;
    @Override
    void pay(Payment payment) {
        // pay
    }
}

class Cash extends PaymentStrategy {
    String cashId;
    @Override
    void pay(Payment payment) {
        // pay
    }
}


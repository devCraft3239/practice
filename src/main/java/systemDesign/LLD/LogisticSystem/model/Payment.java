package systemDesign.LLD.LogisticSystem.model;

import systemDesign.LLD.utility.BillingItem;

import java.util.List;

public class Payment {
    String id;
    String paymentType;
    PaymentStatus paymentStatus;
    Double amount;
    String userId;
    List<BillingItem> billingItems;
}

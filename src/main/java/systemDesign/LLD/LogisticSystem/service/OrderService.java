package systemDesign.LLD.LogisticSystem.service;

import org.aspectj.weaver.ast.Or;
import systemDesign.LLD.LogisticSystem.DTO.OrderRequest;
import systemDesign.LLD.LogisticSystem.model.Order;
import systemDesign.LLD.utility.*;


import javax.xml.stream.Location;

class OrderService{

    private ChargeService chargeService;
    private PaymentService paymentService;

    private NotificationService notificationService;
    void saveOrder(Order order){
    }
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        // set all the fields of order
        saveOrder(order);
        chargeService.calculateCharge(order);

        // make payment
        paymentService.processPayment(PaymentType.UPI, Payment.builder().build());

        // send notification
        notificationService.sendNotification(NotificationType.SMS, Notification.builder().build());
    }

    public void updateOrderStatus(Order order){
        // update order status
        // send notification
    }
}

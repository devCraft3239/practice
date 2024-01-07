package systemDesign.LLD.LogisticSystem.model;

import lombok.Data;
import systemDesign.LLD.utility.Address;
import systemDesign.LLD.utility.Location;

@Data
public class Order {
    String id;
    Item item;
    User sender;
    Location senderAddress;
    Location recipientAddress;
    OrderStatus currentStatus;
    Vehicle vehicle;
    Double charge;
    String paymentId;
    String awbId;
}

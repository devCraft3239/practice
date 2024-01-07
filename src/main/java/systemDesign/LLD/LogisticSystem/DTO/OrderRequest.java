package systemDesign.LLD.LogisticSystem.DTO;

import lombok.Data;

@Data
public class OrderRequest {
    private String orderId;
    private String source;
    private String destination;
    private String vehicleType;
    private String vehicleNumber;
    private String orderStatus;
    private String charge;
    private String distance;
    private String trackingId;

}

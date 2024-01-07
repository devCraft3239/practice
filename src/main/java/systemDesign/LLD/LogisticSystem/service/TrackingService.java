package systemDesign.LLD.LogisticSystem.service;

import systemDesign.LLD.LogisticSystem.model.Awb;
import systemDesign.LLD.LogisticSystem.model.Order;

class TrackingService{
    public void trackOrder(Order order){
        // get awb
        String awb = order.getAwbId();
        // get current location
        // get current status
        // get current vehicle
    }
}

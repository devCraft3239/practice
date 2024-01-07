package systemDesign.LLD.LogisticSystem.service;

import systemDesign.LLD.LogisticSystem.model.Order;
import systemDesign.LLD.utility.Location;


class ChargeService {

    private DistanceService distanceService;

    public ChargeService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }
    public void calculateCharge(Order order) {
        long distance =  distanceService.calculateDistance(order.getSenderAddress(), order.getRecipientAddress());
        double weight = order.getItem().getWeight();
        double charge = distance * weight * 0.5;
        order.setCharge(charge);
    }
}

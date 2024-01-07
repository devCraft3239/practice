package systemDesign.LLD.LogisticSystem;

import javax.xml.stream.Location;

/**
 * The system can take an order to deliver it to a given destination.
 * The order will be a list of items and there is a cost of each order to process.
 * User has to register himself / herself to use this system.
 * User can track his / her order.
 * Orders will be shipped by bike or truck, but only a single order will be shipped by a single vehicle.
 * <p>
 * functional requirement:
 * 1. place an order
 * 2. update the status of order
 * 3. track the order
 * 4. calculate the charge
 * 5. calculate the distance between two locations
 * 6. calculate the charge based on weight of item and distance between two locations.
 * 7. user resgistration/login
 * <p>
 * use-case diagram:
 * System:
 * - send notification
 * - send email
 * - send sms
 * - send push notification
 * - calculate charge
 * - charge based on weight
 * - charge based on distance
 * - calculate distance using google map api
 * - update order status
 * - generate awb
 * User:
 * - user registration/login
 * - track order
 * - payment
 * Agent:
 * - place order
 * - add vehicle
 * - assign vehicle to order
 * <p>
 * class diagram:
 * User:
 * - Person
 * - Agent
 * - System
 * Order
 * Item - Builder pattern
 * Vehicle - factory pattern
 * - Bike
 * - Truck
 * - Car
 * Location
 * Payment - factory pattern
 * - UPI    - singleton
 * - Card   - singleton
 * - Cash   - singleton
 * Notification - factory pattern
 * - PUSH
 * - EMAIL
 * - SMS
 * AWB
 */
public class LogisticSystem {
}



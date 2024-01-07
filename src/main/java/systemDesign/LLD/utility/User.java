package systemDesign.LLD.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String id;
    String name;
    String email;
}

class Admin extends User{
    String username;
    String password;
}
class Customer extends User {
    String username;
    String password;

    String phoneNumber;
}

class FrontDesk extends User {
    String username;
    String password;

    String employeeId;
}

class DeliveryPerson extends User {
    String username;
    String password;

    String employeeId;
}

class Vendor extends User {
    String username;
    String password;

    String vendorId;
}

class UserFactory {
    public static User getUser(String userType) {
        if (userType == null) {
            return null;
        }
        if (userType.equalsIgnoreCase("ADMIN")) {
            return new Admin();
        } else if (userType.equalsIgnoreCase("CUSTOMER")) {
            return new Customer();
        } else if (userType.equalsIgnoreCase("FRONTDESK")) {
            return new FrontDesk();
        } else if (userType.equalsIgnoreCase("DELIVERYPERSON")) {
            return new DeliveryPerson();
        } else if (userType.equalsIgnoreCase("VENDOR")) {
            return new Vendor();
        }
        return null;
    }
}
package main.java.LLD;

import java.util.*;

/**
 User: Each user should have a userId, name, email, mobile number.

 Expense: Could either be EQUAL, EXACT or PERCENT

 Users can add any amount, select any type of expense and split with any of the available users.

 The percent and amount provided could have decimals upto two decimal places.

 In case of percent, you need to verify if the total sum of percentage shares is 100 or not.

 In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.

 The application should have a capability to show expenses for a single user as well as balances for everyone.

 When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.

 The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.

 input:
 You can create a few users in your main method. No need to take it as input.

 There will be 3 types of input:

 Expense in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

 Show balances for all: SHOW

 Show balances for a single user: SHOW <user-id>

 Output:
 When asked to show balance for a single user. Show all the balances that user is part of:

 Format: <user-id-of-x> owes <user-id-of-y>: <amount>

 If there are no balances for the input, print No balances

 In cases where the user for which balance was asked for, owes money, they’ll be x. They’ll be y otherwise.


 * */

public class SplitWise2 {

}

class User{
    String userId;
    String name;
    String email;
    String mobile_number;

    public User(String userId, String name, String email, String mobile_number) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile_number = mobile_number;
    }
}

enum SplitType{
    EQUAL,
    EXACT,
    PERCENTAGE
}

abstract class Split{
    String userId;
    double amount;

    public Split(String userId) {
        this.userId = userId;
    }
}

class EqualSplit extends Split{

    public EqualSplit(String userId) {
        super(userId);
    }
}

class ExactSplit extends Split{

    public ExactSplit(String userId, double amount) {
        super(userId);
        this.amount = amount;
    }

}

class PercentageSplit extends Split{
    double percentage;


    public PercentageSplit(String userId, Double percentage) {
        super(userId);
        this.percentage =  percentage;
    }
}

abstract class Expense{
    String id;
    User paidBy;
    double amount;
    List<Split> splits;

    public Expense(User paidBy, double amount, List<Split> splits) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public abstract boolean validate();
}

class EqualExpense extends Expense{

    public EqualExpense(User paidBy, double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        for (Split split: splits) {
            if (!(split instanceof EqualSplit))
                return false;
        }
        return true;
    }
}

class ExactExpense extends Expense{

    public ExactExpense(User paidBy, double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        for (Split split: splits) {
            if (!(split instanceof ExactSplit))
                return false;
        }
        double sumAmount =  splits.stream().map(split -> (ExactSplit)split)
                .mapToDouble(exactSplit -> exactSplit.amount)
                .reduce((amount1, amount2) -> amount1+amount2).getAsDouble();
        return sumAmount == amount;
    }

}

class PercentageExpense extends Expense{

    public PercentageExpense(User paidBy, double amount, List<Split> splits) {
        super(paidBy, amount, splits);
    }

    @Override
    public boolean validate() {
        for (Split split: splits) {
            if (!(split instanceof PercentageSplit))
                return false;
        }
        double sumPerc =  splits.stream()
                .map(split -> (PercentageSplit)split)
                .mapToDouble(percentageSplit -> percentageSplit.percentage)
                .reduce((perc1, perc2) -> perc1+perc2).getAsDouble();
        return sumPerc == 100.0;
    }

}

class ExpenseService{
    public static Expense createExpense(SplitType splitType, User paidBy, double amount, List<Split> splits){
        switch (splitType){
            case EQUAL:
                int splitSize = splits.size();
                double splittedAmount = amount/splitSize;
                for (Split sp :splits) {
                    sp.amount =  splittedAmount;
                }
                return new EqualExpense(paidBy, amount, splits);
            case EXACT:
                return new ExactExpense(paidBy, amount, splits);
            case PERCENTAGE:
                for (Split sp: splits) {
                    PercentageSplit pSp =  (PercentageSplit) sp;
                    sp.amount =  (amount * pSp.percentage)/ 100.0;
                }
                return new PercentageExpense(paidBy, amount, splits);
            default:
                return null;
        }
    }
}

class ExpenseManager{
    static List<Expense> expenses;
    static Map<String, User> userMap;
    static Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user) {
        userMap.put(user.userId, user);
        balanceSheet.put(user.userId, new HashMap<String, Double>());
    }

    public static void addExpense(SplitType splitType, String paidBy, double amount, List<Split> splits){
        Expense expense = ExpenseService.createExpense(splitType, userMap.get(paidBy),amount, splits);
        expenses.add(expense);
        for (Split sp : splits) {
            String paidTo = sp.userId;

            updateBalanceSheet(paidBy, paidTo, sp.amount);
//            Map<String, Double> balance =  balanceSheet.get(paidBy);
//            balance.put(paidTo, balance.getOrDefault(paidTo, 0.0)+sp.amount);
//            balanceSheet.put(paidBy, balance);

            updateBalanceSheet(paidTo, paidBy, sp.amount * -1);
//            balance = balanceSheet.get(paidTo);
//            balance.put(paidBy, balance.getOrDefault(paidBy, 0.0)-sp.amount);
//            balanceSheet.put(paidTo, balance);
        }
    }

    public static void updateBalanceSheet(String userId1, String userId2, double amount){
        Map<String, Double> balance =  balanceSheet.get(userId1);
        balance.put(userId2, balance.getOrDefault(userId2, 0.0)+amount);
        balanceSheet.put(userId1, balance);
    }

    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userMap.get(user1).name;
        String user2Name = userMap.get(user2).name;
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }
}

class Driver {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addUser(new User("u1", "User1", "gaurav@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u2", "User2", "sagar@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u3", "User3", "hi@workat.tech", "9876543210"));
        expenseManager.addUser(new User("u4", "User4", "mock-interviews@workat.tech", "9876543210"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW":
                    if (commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(commands[4 + i]));
                            }
                            expenseManager.addExpense(SplitType.EQUAL, paidBy, amount, splits);
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new ExactSplit(commands[4 + i], Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(SplitType.EXACT,paidBy,amount, splits);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentageSplit(commands[4 + i], Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(SplitType.PERCENTAGE,paidBy, amount,splits);
                            break;
                    }
                    break;
            }
        }
    }
}

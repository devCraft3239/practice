package LLD;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * design splitwise app which support following operations
 * 1. add user
 * 2. add expense
 * 3. divide an expense exactly as it was added equally between users
 * 4. divided an expense between any no of users with exact amount
 * 5. divided an expense between any no of users and also add amount/percentage share
 * 6. show balances for a user
 */
public interface SplitWise {
    void addUser(User user);
    void addExpense(Expense expense);
    void showBalance(int userId);
    void showBalanceSheet();
}

@Data
@AllArgsConstructor
class User{
    int id;
    String name;
}

@Data
class Split{
    int userId;
    double amount;
    Split(int userId){
        this.userId = userId;
    }
}

class EqualSplit extends Split{
    EqualSplit(int userId){
        super(userId);
    }
}

class ExactSplit extends Split{
    ExactSplit(int userId, double amount){
        super(userId);
        this.amount = amount;
    }
}

class PercentageSplit extends Split{
    int percentage;
    PercentageSplit(int userId, int percentage){
        super(userId);
        this.percentage = percentage;
    }
}

enum ExpenseType{
    EQUAL,
    EXACT,
    PERCENTAGE
}
@Data
@AllArgsConstructor
abstract class Expense{
    int id;
    int amount;
    int  paidBy;
    List<Split> splits;
    ExpenseType expenseType;
    abstract boolean validate();
}

class EqualExpense extends Expense{
    EqualExpense(int id, int amount, int paidBy, List<Split> splits){
        super(id, amount, paidBy, splits, ExpenseType.EQUAL);
    }

    @Override
    boolean validate() {
        return true;
    }
}

class ExactExpense extends Expense{
    ExactExpense(int id, int amount, int paidBy, List<Split> splits){
        super(id, amount, paidBy, splits, ExpenseType.EXACT);
    }

    @Override
    boolean validate() {
        return true;
    }
}

class PercentageExpense extends Expense{
    PercentageExpense(int id, int amount, int paidBy, List<Split> splits){
        super(id, amount, paidBy, splits, ExpenseType.PERCENTAGE);
    }

    @Override
    boolean validate() {
        return true;
    }
}

class ExpenseService{
    public static Expense createExpense(int id, int amount, int paidBy, List<Split> splits, ExpenseType expenseType){
        switch (expenseType){
            case EQUAL:
                for (Split split : splits){
                    split.amount = amount *1.0/ splits.size();
                }
                return new EqualExpense(id, amount, paidBy, splits);
            case EXACT:
                return new ExactExpense(id, amount, paidBy, splits);
            case PERCENTAGE:
                for (Split split : splits){
                    PercentageSplit percentageSplit = (PercentageSplit) split;
                    percentageSplit.amount = amount * percentageSplit.percentage / 100;
                    split.setAmount(percentageSplit.amount);
                }
                return new PercentageExpense(id, amount, paidBy, splits);
            default:
                return null;
        }
    }
}

class SplitWiseImpl implements SplitWise{
    List<User> users;
    List<Expense> expenses;

    Map<Integer, Map<Integer, Double>> balanceSheet;

    SplitWiseImpl(){
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }
    SplitWiseImpl(List<User> users, List<Expense> expenses, Map<Integer, Map<Integer, Double>> map){
        this.users = users;
        this.expenses = expenses;
        this.balanceSheet = map;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
        for (Split split : expense.splits){
            Map<Integer, Double> userSheet = balanceSheet.getOrDefault(expense.paidBy, new HashMap<>());
            if (expense.paidBy != split.userId){
                userSheet.put(split.userId, userSheet.getOrDefault(split.userId, 0.0) - split.amount);
                balanceSheet.put(expense.paidBy, userSheet);
                userSheet = balanceSheet.getOrDefault(split.userId, new HashMap<>());
                userSheet.put(expense.paidBy, userSheet.getOrDefault(expense.paidBy, 0.0) + split.amount);
            }
        }
    }

    @Override
    public void showBalance(int userId) {
        Map<Integer, Double> userSheet = balanceSheet.getOrDefault(userId, new HashMap<>());
        for (Map.Entry<Integer, Double> entry : userSheet.entrySet()){
            System.out.println("User "+userId+" owes "+entry.getKey()+" amount "+entry.getValue());
        }
        System.out.println("--------------------");
    }

    @Override
    public void showBalanceSheet(){
        System.out.println("Balance Sheet");
        System.out.println("-------------");
        for (Map.Entry<Integer, Map<Integer, Double>> entry : balanceSheet.entrySet()){
            System.out.println("User "+entry.getKey()+" owes");
            for (Map.Entry<Integer, Double> entry1 : entry.getValue().entrySet()){
                System.out.println("User "+entry1.getKey()+" amount "+entry1.getValue());
            }
            System.out.println("--------------------");
        }
    }
}

class SplitWiseTest{
    public static void main(String[] args) {
        SplitWise splitWise = new SplitWiseImpl();
        splitWise.addUser(new User(1, "A"));
        splitWise.addUser(new User(2, "B"));
        splitWise.addUser(new User(3, "C"));
        splitWise.addExpense(ExpenseService.createExpense(1, 300, 1, Arrays.asList(new Split(1), new Split(2), new Split(3)), ExpenseType.EQUAL));
        splitWise.addExpense(ExpenseService.createExpense(2, 600, 2, Arrays.asList(new Split(1), new Split(2), new Split(3)), ExpenseType.EQUAL));
        splitWise.addExpense(ExpenseService.createExpense(3, 300, 3, Arrays.asList(new Split(1), new Split(2), new Split(3)), ExpenseType.EQUAL));
        splitWise.showBalance(1);
        splitWise.showBalance(2);
        splitWise.showBalance(3);
        splitWise.addExpense(ExpenseService.createExpense(4, 200, 1,Arrays.asList(new ExactSplit(1, 100), new ExactSplit(2, 50), new ExactSplit(3, 50)), ExpenseType.EXACT));
        splitWise.addExpense(ExpenseService.createExpense(5, 200, 2,Arrays.asList(new ExactSplit(1, 50), new ExactSplit(2, 100), new ExactSplit(3, 50)), ExpenseType.EXACT));
        splitWise.showBalance(1);
        splitWise.showBalance(2);
        splitWise.showBalance(3);
    }
}


package design.pattern.behavioral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * In Strategy design pattern we define multiple strategy/algorithm to perform a single task
 client decides the actual implementation to be used at runtime.
 * */
public class StrategyDesignPattern {
}

class Employee{
    int id;
    String name;

    Employee(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

interface SortStrategy extends Comparator<Employee>{
}

class SortById implements SortStrategy{
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.id == e2.id ? 0 : e1.id < e2.id ? -1 : 1;
    }
}

class SortByName implements SortStrategy{
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.name.compareTo(e2.name);
    }
}

class ClientRunner{
    List<Employee> employees;
    ClientRunner(List<Employee> employees){
        this.employees = employees;
    }
    public void sort(SortStrategy sortStrategy){
        Collections.sort(employees, sortStrategy);
        employees.stream().forEach(employee -> System.out.println(employee));
    }
}

class RunnerMain{
    public static void main(String[] args) {
        List<Employee> employees =  new ArrayList<>();
        employees.add(new Employee(1, "B"));
        employees.add(new Employee(2,"A"));
        employees.add(new Employee(3, "D"));
        employees.add(new Employee(4, "C"));
        ClientRunner clientRunner =  new ClientRunner(employees);

        SortStrategy sortById = new SortById();
        clientRunner.sort(sortById);
        System.out.println("-------------------");
        SortStrategy sortByName = new SortByName();
        clientRunner.sort(sortByName);
    }
}
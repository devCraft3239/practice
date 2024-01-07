import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test
{
    public static void main(String[] args) {
        List<Employee> list =  new ArrayList<>();
        list.add(new Employee("1", "A", "IT", 25, 10000));
        list.add(new Employee("2", "B", "IT", 26, 20000));
        list.add(new Employee("3", "C", "CSE", 27, 30000));
        list.add(new Employee("4", "D", "IT", 28, 40000));
        list.add(new Employee("5", "E", "CSE", 29, 50000));
        list.add(new Employee("6", "F", "CSE", 30, 60000));
        list.add(new Employee("7", "G", "IT", 31, 70000));
        // list of employee
        // id, name, age, salary
        // group by name
        list.stream().collect(Collectors.groupingBy(Employee::getDept)); // return dept --> list of employee
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toList()))); // return dept --> list of name
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));  // return dept --> count
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summingInt(Employee::getSalary))); // return dept --> sum of salary
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparing(Employee::getSalary)))); // return dept --> max salary
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.minBy(Comparator.comparing(Employee::getSalary)))); // return dept --> min salary
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingInt(Employee::getSalary))); // return dept --> avg salary
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summarizingInt(Employee::getSalary)));// return dept --> summary of salary
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.joining(", ")))) ;// return dept --> comma separated name
        list.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.joining(", ", "[", "]")))) ;// return dept --> comma separated name with prefix and suffix
}
}

@Data
@AllArgsConstructor
class Employee{
    String id;
    String name;
    String dept;
    int age;
    int salary;
}

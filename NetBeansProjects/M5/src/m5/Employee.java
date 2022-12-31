package m5;

/**
 *
 * @author Adrian
 */
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String name;
    private int empId;
    private int salary;

    public Employee(int id, String name, int sal) {
        this.empId = id;
        this.name = name;
        this.salary = sal;
    }

    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;
        boolean status = false;

        if (this.name.equalsIgnoreCase(emp.name)
                && this.empId == emp.empId
                && this.salary == emp.salary) {
            status = true;
        }

        return status;
    }

    public static String getEmpNameWithHighestSalary() {
        return "Peter";
    }

    public static Employee getHighestPaidEmployee() {
        return (new Employee(1, "Peter", 15000));
    }

    public static Employee getSmartestEmployee() {
        return (new Employee(2, "Oscar", 120));
    }
}

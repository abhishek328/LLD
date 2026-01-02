import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Stream2 {

    public static void main(String[] args) {
        /*
        Level 2 — Grouping, Mapping, Collectors (Most Interviewed)
        Focus: groupingBy, mapping, reducing
       */

        Employee employee1 = new Employee("Abhishek", "CSE", 20000);
        Employee employee2 = new Employee("Abhishek", "ECE", 20000);
        Employee employee3 = new Employee("Abhishek", "CSE", 40000);
        Employee employee4 = new Employee("RAM", "CSE", 40000);
        Employee employee5 = new Employee("RAM", "MECH", 50000);
        Employee employee6 = new Employee("Ravi", "EE", 60000);

        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6);

        //Group employees by department.
        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)).values());

        //Group employees by department and collect only names.
        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)).values()
                .stream().map(t-> t.stream().map(Employee::getName)).collect(Collectors.toList()));

        //Find the average salary per department.

        //Find the highest-paid employee per department.

        //Count employees per department.

        //Partition employees into salary ≥ 50k and < 50k.

        //Group employees by department and sort each group by salary descending.

        //Convert employees into Map<String, Integer> (name → salary).

        //Handle duplicate keys while collecting to a map.

        //Find departments having more than 3 employees.



    }

    static class Employee {
        String name;
        String department;
        int salary;

        public Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return salary == employee.salary && Objects.equals(name, employee.name) && Objects.equals(department, employee.department);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, department, salary);
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}

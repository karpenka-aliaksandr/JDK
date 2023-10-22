package hw04;

public class Main {
    public static void main(String[] args) {
        Employees employees = new Employees();
        employees.add(new Employee("+375295518500", "Aliaksandr", "Karpenka", 20));
        employees.add(new Employee("+375445992221", "Syarhei", "Karpenka", 20));
        employees.add(new Employee("+375445992222", "Syarhei", "Buretc", 18));
        System.out.println("all employees:\n" + employees);
        System.out.println("all employees with experience = 20:\n" + employees.findEmployeesByExperience(20));
        System.out.println("all employees with experience = 21:\n" + employees.findEmployeesByExperience(21));
        System.out.println("all number by surname = Karpenka:\n" + employees.findNumberBySurname("Karpenka").toStringOnlyNumberAndSurname());
        System.out.println("all number by surname = Filipau:\n" + employees.findNumberBySurname("Filipau").toStringOnlyNumberAndSurname());
        System.out.println("employee with id = 2:\n" + employees.findEmployeesById(2));


    }
}

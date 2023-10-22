package hw04;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Employees {
    private ArrayList<Employee> employees;

    public Employees() {
        this.employees = new ArrayList<Employee>();
    }
    
    public Employees(ArrayList<Employee>employees) {
        this.employees = employees;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append(employee + "\n");
        }
        if (sb.length() == 0) sb.append("The list is empty.\n");
        return sb.toString();
    }   

    public void add(Employee employee){
        employees.add(employee);
    }

    public Employees findEmployeesByExperience(int experience){ 
        return (new Employees((ArrayList<Employee>)(employees.
                                    stream().
                                    filter(employee -> employee.getExperience() == experience).
                                    sorted(Comparator.comparing(Employee::getSurname)).
                                    collect(Collectors.toList()))));      
    }
    
    public String toStringOnlyNumberAndSurname() {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append(employee.toStringOnlyNumberAndSurname() + "\n");
        }
        if (sb.length() == 0) sb.append("The list is empty.\n");
        return sb.toString();
    }  
    
    public Employees findNumberBySurname(String surname){ 
        return (new Employees((ArrayList<Employee>)(employees.
                                    stream().
                                    filter(employee -> employee.getSurname() == surname).
                                    sorted(Comparator.comparing(Employee::getSurname)).
                                    collect(Collectors.toList()))));      
    }

    public Employees findEmployeesById(Integer id){ 
        return (new Employees((ArrayList<Employee>)(employees.
                                    stream().
                                    filter(employee -> employee.getId() == id).
                                    sorted(Comparator.comparing(Employee::getSurname)).
                                    collect(Collectors.toList()))));      
    }

}

package hw04;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final Integer id;
    private String name;
    private String surname;
    private int experience;
    private String phone;
    
    public Employee(String phone, String name, String surname, int experience) {
        this.id = COUNTER.getAndIncrement();
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.experience = experience;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id:" + id.toString());
        sb.append(", phone:" + phone);
        sb.append(", name:" + name);
        sb.append(", surname:" + surname);
        sb.append(", experience:" + experience + "}");
        return sb.toString();
    }

    
    public String toStringOnlyNumberAndSurname() {
        StringBuilder sb = new StringBuilder();
        sb.append("{phone:" + phone);
        sb.append(", surname:" + surname + "}");
        return sb.toString();
    }

    public String getName() {
        return name;
    }
    public int getExperience() {
        return experience;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhone() {
        return phone;
    }
    public Integer getId() {
        return id;
    }

}

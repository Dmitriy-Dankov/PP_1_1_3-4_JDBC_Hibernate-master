package jm.task.core.jdbc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Byte age;

    public User() {}

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.lastName = user.lastName;
        this.age = user.age;
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        Object[] obj = new Object[]{id, name, lastName, age};
        return String.format("User: id=%d, name=%s, last_name=%s, age=%d", obj);
    }
}

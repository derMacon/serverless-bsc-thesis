package dps.hoffmann.queue_demo.model;

import lombok.Builder;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Builder
@ToString
public class Employee implements Serializable {

    private int id;
    private String name;
    private float salary;

    public Employee() {}
    public Employee(int id, String name, float salary) {
        super();
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    @XmlAttribute
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
}

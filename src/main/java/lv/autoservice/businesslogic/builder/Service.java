package lv.autoservice.businesslogic.builder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "work")
    private String work;

    @Column(name = "datetime")
    private String dateTime;

    @Column(name = "email", nullable = false)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id) &&
                Objects.equals(model, service.model) &&
                Objects.equals(work, service.work) &&
                Objects.equals(dateTime, service.dateTime) &&
                Objects.equals(email, service.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, work, dateTime, email);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", work='" + work + '\'' +
                ", time='" + dateTime + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

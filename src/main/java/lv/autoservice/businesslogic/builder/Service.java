package lv.autoservice.businesslogic.builder;

import javax.persistence.*;

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

    @Column(name = "status")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (!id.equals(service.id)) return false;
        if (model != null ? !model.equals(service.model) : service.model != null) return false;
        if (work != null ? !work.equals(service.work) : service.work != null) return false;
        if (dateTime != null ? !dateTime.equals(service.dateTime) : service.dateTime != null) return false;
        if (email != null ? !email.equals(service.email) : service.email != null) return false;
        return status != null ? status.equals(service.status) : service.status == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", work='" + work + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

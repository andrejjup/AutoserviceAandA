package lv.autoservice.businesslogic.builder;

public class ServiceBuilder {
    private Long id;
    private String model;
    private String work;
    private String dateTime;
    private String email;
    private String status;

    private ServiceBuilder() {}

    public static ServiceBuilder createService() {
        return new ServiceBuilder();
    }

    public Service build() {
        Service service = new Service();
        service.setId(id);
        service.setModel(model);
        service.setWork(work);
        service.setDateTime(dateTime);
        service.setEmail(email);
        service.setStatus(status);
        return service;
    }

    public ServiceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ServiceBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public ServiceBuilder withWork(String work) {
        this.work = work;
        return this;
    }

    public ServiceBuilder withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public ServiceBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ServiceBuilder withStatus(String status) {
        this.status = status;
        return this;
    }
}

package lv.autoservice.businesslogic.autoservicereservation;

public class AutoServiceReservationRequest {
    private String model;
    private String work;
    private String dateTime;
    private String email;

    public AutoServiceReservationRequest(String model, String work, String dateTime, String email) {
        this.model= model;
        this.work= work;
        this.dateTime= dateTime.trim();
        this.email = email;
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
}

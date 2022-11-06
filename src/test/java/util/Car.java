package util;

public class Car {

    private  String registration;
    private String makeAndModel;

    public Car(String registration, String makeAndModel) {
        this.registration = registration;
        this.makeAndModel = makeAndModel;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public void setMakeAndModel(String makeAndModel) {
        this.makeAndModel = makeAndModel;
    }
}

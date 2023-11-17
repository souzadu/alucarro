package VO;

public class VehicleVO {
    private int id;
    private String make;
    private String model;
    private String plate;
    private Float daily_rate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Float getDailyRate() {
        return daily_rate;
    }

    public void setDailyRate (String daily_rate) {
        this.daily_rate = Float.valueOf(daily_rate);
    }
}
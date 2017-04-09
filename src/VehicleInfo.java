/**
 * Created by bsheen on 4/7/17.
 */
public class VehicleInfo {

    private String VIN;
    private double odometer;
    private double consumption;
    private double odometerReadingLastOilChange;
    private String engineSize;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerReadingLastOilChange() {
        return odometerReadingLastOilChange;
    }

    public void setOdometerReadingLastOilChange(double odometerReadingLastOilChange) {
        this.odometerReadingLastOilChange = odometerReadingLastOilChange;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public double milesPerGallon(){
        return odometer/consumption;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "VIN='" + VIN + '\'' +
                ", odometer=" + odometer +
                ", consumption=" + consumption +
                ", odometerReadingLastOilChange=" + odometerReadingLastOilChange +
                ", engineSize='" + engineSize + '\'' +
                '}';
    }
}

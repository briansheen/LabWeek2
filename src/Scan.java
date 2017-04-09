/**
 * Created by bsheen on 4/7/17.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Scan {

    public static void main(String[] args) throws IOException {

        Telematics carReport = new TelematicsImpl();
        VehicleInfo vehicleInfo = new VehicleInfo();
        Scanner s = null;
        try{
            s = new Scanner(System.in);
            System.out.print("input vehicle VIN number: ");
            while(s.hasNext()){
                String input = s.next();
                vehicleInfo.setVIN(input);
                System.out.print("input odometer: ");
                input = s.next();
                vehicleInfo.setOdometer(Double.valueOf(input));
                System.out.print("input consumption: ");
                input = s.next();
                vehicleInfo.setConsumption(Double.valueOf(input));
                System.out.print("input odometer reading from last oil change: ");
                input = s.next();
                vehicleInfo.setOdometerReadingLastOilChange(Double.valueOf(input));
                System.out.print("input engine size: ");
                input = s.next();
                vehicleInfo.setEngineSize(input);
                carReport.report(vehicleInfo);
                System.out.print("input vehicle VIN number: ");
            }
        } finally {
            if(s != null){
                s.close();
            }
        }
    }
}
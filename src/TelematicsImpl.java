import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;

/**
 * Created by bsheen on 4/7/17.
 */
public class TelematicsImpl implements Telematics {
    private ObjectMapper mapper = new ObjectMapper();
    private VehicleInfo[] vehicleInfos = new VehicleInfo[10];
    private String carFile;
    private String VINjson;
    private String HTML_TOP_WRITE;
    private String[] TABLE_ROW_WRITE = new String[10];

    @Override
    public void report(VehicleInfo vehicleInfo) throws IOException {
        carFile = mapper.writeValueAsString(vehicleInfo);
        double totOdometer = 0;
        double totConsumption = 0;
        double totLastOilChange = 0;
        double totEngineSize = 0;
        double totMpg = 0;
        int divisor = 0;

        try (PrintWriter out = new PrintWriter(vehicleInfo.getVIN() + ".json")) {
            out.println(carFile);
            out.flush();
           System.out.println(carFile);
        }

        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                ++divisor;
                try (BufferedReader in = new BufferedReader(new FileReader(f.getName()))) {
                    carFile = in.readLine();
                    for (int i = 0; i < vehicleInfos.length; ++i) {
                        if (vehicleInfos[i] != null) {
                            VINjson = vehicleInfos[i].getVIN() + ".json";
                            if (VINjson.equals(f.getName())) {
                                vehicleInfos[i] = mapper.readValue(carFile, VehicleInfo.class);
                                totOdometer += vehicleInfos[i].getOdometer();
                                totConsumption += vehicleInfos[i].getConsumption();
                                totEngineSize += Double.valueOf(vehicleInfos[i].getEngineSize());
                                totLastOilChange += vehicleInfos[i].getOdometerReadingLastOilChange();
                                totMpg += vehicleInfos[i].milesPerGallon();
                                break;
                            }
                        } else if(vehicleInfos[i] == null){
                            vehicleInfos[i] = mapper.readValue(carFile, VehicleInfo.class);
                            totOdometer += vehicleInfos[i].getOdometer();
                            totConsumption += vehicleInfos[i].getConsumption();
                            totEngineSize += Double.valueOf(vehicleInfos[i].getEngineSize());
                            totLastOilChange += vehicleInfos[i].getOdometerReadingLastOilChange();
                            totMpg += vehicleInfos[i].milesPerGallon();
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("total odometer is " + totOdometer);
        System.out.println("total consumption is " + totConsumption);
        System.out.println("total last oil change is " + totLastOilChange);
        System.out.println("total engine size is " + totEngineSize);
        System.out.println("total number of vehicles " + divisor);

        HTML_TOP_WRITE = Telematics.HTML_TOP.replace("%count%",String.valueOf(divisor));
        HTML_TOP_WRITE = HTML_TOP_WRITE.replace("%odometer%",String.format("%.1f",totOdometer/divisor));
        HTML_TOP_WRITE = HTML_TOP_WRITE.replace("%consumption%",String.format("%.1f",totConsumption/divisor));
        HTML_TOP_WRITE = HTML_TOP_WRITE.replace("%lastoilchange%",String.format("%.1f",totLastOilChange/divisor));
        HTML_TOP_WRITE = HTML_TOP_WRITE.replace("%enginesize%",String.format("%.1f",totEngineSize/divisor));
        HTML_TOP_WRITE = HTML_TOP_WRITE.replace("%mpg%",String.format("%.1f",totMpg/divisor));

        for(int i = 0; i < divisor; ++i){
            TABLE_ROW_WRITE[i] = Telematics.TABLE_ROW_HTML.replace("%vin%",vehicleInfos[i].getVIN());
            TABLE_ROW_WRITE[i] = TABLE_ROW_WRITE[i].replace("%odometer%",String.valueOf(vehicleInfos[i].getOdometer()));
            TABLE_ROW_WRITE[i] = TABLE_ROW_WRITE[i].replace("%consumption%",String.valueOf(vehicleInfos[i].getConsumption()));
            TABLE_ROW_WRITE[i] = TABLE_ROW_WRITE[i].replace("%lastoilchange%",String.valueOf(vehicleInfos[i].getOdometerReadingLastOilChange()));
            TABLE_ROW_WRITE[i] = TABLE_ROW_WRITE[i].replace("%enginesize%",String.format("%.1f",Double.valueOf(vehicleInfos[i].getEngineSize())));
            TABLE_ROW_WRITE[i] = TABLE_ROW_WRITE[i].replace("%mpg%",String.format("%.1f",vehicleInfos[i].milesPerGallon()));
        }

        try(PrintWriter out = new PrintWriter("Dashboard.html")){
            out.println(HTML_TOP_WRITE);
            for(int i = 0; i < divisor; ++i){
                out.println(TABLE_ROW_WRITE[i]);
            }
            out.println(Telematics.HTML_FOOTER);
            out.flush();
        }
    }

    @Override
    public String toString() {
        return "TelematicsImpl{" +
                "vehicleInfos=" + Arrays.toString(vehicleInfos) +
                '}';
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by bsheen on 4/7/17.
 */
public interface Telematics {

    void report(VehicleInfo car) throws IOException;

    String HTML_TOP = "<html>\n" +
            "<title>Vehicle Telematics Dashboard</title>\n" +
            "<body>\n" +
            "<h1 align=\"center\">Averages for %count% vehicles</h1>\n" +
            "<table align=\"center\">\n" +
            "    <tr>\n" +
            "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters) |</th><th>Miles Per Gallon </th>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td align=\"center\">%odometer%</td><td align=\"center\">%consumption%</td><td align=\"center\">%lastoilchange%</td align=\"center\"><td align=\"center\">%enginesize%</td><td align=\"center\">%mpg%</td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "<h1 align=\"center\">History</h1>\n" +
            "<table align=\"center\" border=\"1\">\n" +
            "    <tr>\n" +
            "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th><th>Miles Per Gallon</th>\n" +
            "    </tr>\n";

    String TABLE_ROW_HTML =
            "        <tr>\n" +
            "            <td align=\"center\">%vin%</td><td align=\"center\">%odometer%</td><td align=\"center\">%consumption%</td><td align=\"center\">%lastoilchange%</td align=\"center\"><td align=\"center\">%enginesize%</td><td align=\"center\">%mpg%</td>\n" +
            "        </tr>\n";

    String HTML_FOOTER = "</table>\n" +
            "</body>\n" +
            "</html>";



}

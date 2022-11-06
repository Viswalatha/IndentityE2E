package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarDetails {

    public List<String> extractRegNumbers() throws IOException {
        List<String> regnumber = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/car_input_v1.txt"));
        String regex = "[A-Z]{2}[0-9]{2}[A-Z]{3}";
        String regex1 = "[A-Z]{2}[0-9]{2} [A-Z]{3}";
        String line;
        Pattern r = Pattern.compile(regex);
        Pattern r1 = Pattern.compile(regex1);
        while ((line = br.readLine()) != null) {
            Matcher m = r.matcher(line);
            Matcher m1 = r1.matcher(line);
            if (m.find()) {
                regnumber.add(m.group());
            }
            if (m1.find()) {
                regnumber.add(m1.group());
            }
        }
        br.close();
        return regnumber;
    }


    public List<Car> expetedCarDetails() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/car_output_v1.txt"));
        List<Car> carDetails = new ArrayList<Car>();

        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0) {
                continue;
            }
            String[] details = line.split("[,]", 2);
            String reg = details[0];
            String makeAndModel = details[1];
            makeAndModel = makeAndModel.replaceFirst(","," ");
            Car p = new Car(reg, makeAndModel);
            carDetails.add(p);
        }
        br.close();
        return carDetails;
    }
}

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pars {
    static SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
    static String head = "#\tВремя\tДействие\tAction Data\tMessage";
    static File input = new File("raw.txt");
    static String output = "scenary_";
    static  String action = "sendMessage";

    public static void main(String[] args) {
        Date date = new Date();
        try (BufferedReader reader = new BufferedReader(new FileReader(input,StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output + formater.format(date) + ".csv",StandardCharsets.UTF_8))){
            writer.write(head + "\n");
            int count = 1;
            String line = "" +  count;
            int countRead = 0;
            while (reader.ready()){
                String s = reader.readLine();
                if (s.isEmpty()){
                    continue;
                }
                line += "\t" + s;
                countRead ++;
                if (countRead == 1){
                    line += ";" + action;
                }else if (countRead > 2){

                    writer.write(line + "\n");
                    count++;
                    countRead = 0;
                    line = "" + count;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
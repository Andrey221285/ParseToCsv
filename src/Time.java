import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    int change;
    String stringTime;
    LocalTime time;

    public Time(String stringTime, int change) {
        this.stringTime = stringTime;
        this.change = change;
        if (stringTime.contains("-")){
            this.time = null;
        } else {
            String[] s = stringTime.split(":");
            if (s.length == 3){
                this.time = LocalTime.of(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
            } else if (s.length == 2) {
                this.time = LocalTime.of(0,Integer.parseInt(s[0]),Integer.parseInt(s[1]));
            } else {
                this.time = LocalTime.of(0,0,Integer.parseInt(s[0]));
            }
        }
    }

    public void changeTime(){
        if (time != null && time.toSecondOfDay() > Math.abs(change) + 10){
            time = time.minusSeconds(change);
        }
    }

    public String getTime(){
        String time = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("m:ss");
        if (this.time == null){
            return stringTime;
        }

        if (this.time.getHour() > 0){
            time = this.time.format(formatter);
        } else {
            time = this.time.format(formatter2);
        }
        return time;
    }

    public static void main(String[] args) {
        Time time = new Time("1:15", 60);
time.changeTime();
        System.out.println(time.getTime());
    }
}

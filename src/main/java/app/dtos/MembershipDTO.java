package app.dtos;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class MembershipDTO {
    public String username, paymentType, fitnessProgram;
    public String card;
    public Date startDate, endDate;

    public MembershipDTO(String card, String username, Date date, String name, String fitnessProgram) {
        this.card = card;
        this.username=username;
        this.startDate=date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        this.endDate = cal.getTime();
        this.paymentType=name;
        this.fitnessProgram=fitnessProgram;
    }
}

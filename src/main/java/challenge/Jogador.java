package challenge;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * @author elvisbaranoski@yahoo.com.br
 */
public class Jogador {
    private String fullName;
    private String club;
    private Integer age;
    private LocalDate birthDate;
    private String nationality;
    private Double eurWage;
    private BigDecimal eurReleaseClause;


    public Jogador(String fullName, String club, Integer age, String birthDate, String nationality, Double eurWage, String eurReleaseClause) throws ParseException {
        this.fullName = fullName;
        this.club = club;
        this.age = age;
        this.nationality = nationality;
        this.birthDate = converterData(birthDate);
        this.eurWage = eurWage;
        this.eurReleaseClause = converterBigData(eurReleaseClause);
    }

    private BigDecimal converterBigData(String urReleaseClause) {
        if(urReleaseClause.equals("")){
            return BigDecimal.valueOf(0);
        }else {
            return new BigDecimal(urReleaseClause);
        }
    }

    public Double getEurWage() { return eurWage; }

    private LocalDate converterData (String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateFormated = LocalDate.parse(date,formatter);
        return dateFormated;
    }
    public LocalDate getBirthDate() { return birthDate; }

    public String getFullName() {
        return fullName;
    }

    public String getClub() {
        return club;
    }

    public Integer getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public BigDecimal getEurReleaseClause() {
        return eurReleaseClause;
    }



}


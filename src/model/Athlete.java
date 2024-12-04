package model;

public class Athlete {
    private String name;
    private int code;
    private int codeSport;
    private String sportName;


    public Athlete(String name, int code, int codeSport, String sportName) {
        this.name = name;
        this.code = code;
        this.codeSport = codeSport;
        this.sportName = sportName;

    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public int getCodeSport() {
        return codeSport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setcodeSport(int codeSport) {
        this.codeSport = codeSport;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", codeSport=" + codeSport +
                ", sportName='" + sportName + '\'' +
                '}';
    }
}

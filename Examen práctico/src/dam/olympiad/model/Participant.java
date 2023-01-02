package dam.olympiad.model;

import java.time.LocalDate;

public class Participant {
    private String dni;
    private String name;
    private String country;
    private LocalDate birthday;
    private int discipline;

    public Participant() {}

    
    public Participant(String dni, String name, String country, LocalDate birthday, int discipline) {
        this.dni = dni;
        this.name = name;
        this.country = country;
        this.birthday = birthday;
        this.discipline = discipline;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public int getDiscipline() {
        return discipline;
    }
    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Participant [dni=" + dni + ", name=" + name + ", country=" + country + ", birthday=" + birthday
                + ", discipline=" + discipline + "]";
    }
}
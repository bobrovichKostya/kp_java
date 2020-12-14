package model;

public class Patient {
    private String name;
    private String surname;
    private int age;
    private String disease;
    private boolean type; // амбулаторный/стационар

    public Patient(String name, String surname, int age, String disease, boolean type) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.disease = disease;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public boolean isType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
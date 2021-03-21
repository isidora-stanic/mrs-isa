package isa9.Farmacy.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {

    private Address address;
    private String phoneNumber;
    private int points;
    private int panalties;
    private List<Examination> myExaminations;

    public Patient() {
    }

    public Patient(Long id, String name, String surname, String email,
                   String password, String username, Address address, String phoneNumber) {
        super(id, username, name, surname, email, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.points = 0;
        this.panalties = 0;
        this.myExaminations = new ArrayList<>();

    }

    public Patient(Long id, String username, String name, String surname, String email,
                   String password, Address address, String phoneNumber, int points,
                   int panalties, List<Examination> myExaminations) {
        super(id, username, name, surname, email, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.points = points;
        this.panalties = panalties;
        this.myExaminations = myExaminations;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Patient: " + getUsername() + " " + getName() + " " + getSurname() + " " + getEmail()
                + " " + getPassword() + " " + phoneNumber + " " + address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPanalties() {
        return panalties;
    }

    public void setPanalties(int panalties) {
        this.panalties = panalties;
    }

    public List<Examination> getMyExaminations() {
        return myExaminations;
    }

    public void setMyExaminations(List<Examination> myExaminations) {
        this.myExaminations = myExaminations;
    }
}

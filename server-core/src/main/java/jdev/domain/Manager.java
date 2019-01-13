package jdev.domain;

import javax.persistence.*;

@Entity
@Table(name="manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MANAGER_ID")
    public int manager_id;
    @Column(name="SURNAME")
    public String surname;
    @Column(name="NAME")
    public String name;
    @Column(name="GIVEN_NAME")
    public String given_name;
    @Column(name="FIO")
    public String fio;
    @Column(name="SEAT")
    public String seat;

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}

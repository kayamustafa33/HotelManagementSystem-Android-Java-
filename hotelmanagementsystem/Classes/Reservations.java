package com.mustafa.hotelmanagementsystem.Classes;

public class Reservations {
    public String custName,custSurname,custPhoneNumber,entryDate,exitDate;

    public Reservations(String custName, String custSurname, String custPhoneNumber, String entryDate, String exitDate) {
        this.custName = custName;
        this.custSurname = custSurname;
        this.custPhoneNumber = custPhoneNumber;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }
}

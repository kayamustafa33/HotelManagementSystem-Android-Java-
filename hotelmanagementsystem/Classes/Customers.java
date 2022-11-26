package com.mustafa.hotelmanagementsystem.Classes;

public class Customers {
    public String custName,custSurname,custIdentityNumber,custPhoneNumber,roomNumber,entryDate,exitDate;

    public Customers(String custName, String custSurname, String custIdentityNumber, String custPhoneNumber, String roomNumber, String entryDate, String exitDate) {
        this.custName = custName;
        this.custSurname = custSurname;
        this.custIdentityNumber = custIdentityNumber;
        this.custPhoneNumber = custPhoneNumber;
        this.roomNumber = roomNumber;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }
}

package com.example.mealhubandroid.Models;



public class ServiceProviderVM {

    String id;
    String firstName;
    String lastName;
    String companyName;
    String companyAddress;
    int contactNo;
    double[] location;


    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public ServiceProviderVM(
            String firstName,
            String lastName,
            String companyName,
            String companyAddress,
            int contactNo,
            double[] location)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.companyName=companyName;
        this.companyAddress=companyAddress;
        this.contactNo=contactNo;
        this.location=location;
    }

    public ServiceProviderVM() {

    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String toString()
    {
        return "Service Provider Id:"+id+" First Name:"+firstName+" Last Name:"+lastName+"" +
                " Company Name:"+companyName+" Company Address:"+companyAddress+"contactNo:" +contactNo+
                " Longitude:"+location[0]+" Latitude:"+location[1]+" ";
    }

}

/**
 Test_Name:TiXiJieGou3
 Date:2023.10.23
 Number:202131061235
 Name:余仕煊
 **/
public class Person {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Phone Number: " + number;
    }
    private  String number;

    public Person(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }


}

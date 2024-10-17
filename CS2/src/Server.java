/**
 Test_Name:TiXiJieGou3
 Date:2023.10.23
 Number:202131060927
 Name:Tang_Zhizhen
 **/
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void editPerson(int index, Person newPerson) {
        persons.set(index, newPerson);
    }

    public void deletePerson(int index) {
        persons.remove(index);
    }

    public Person getPerson(int index) {
        return persons.get(index);
    }
    public  void  systemInit() throws IOException {
        String str;
        String s[]=new String[4];
        File f=new File("persons.txt");

        FileReader f1=new FileReader(f);
        BufferedReader f2=new BufferedReader(f1);
        f2.readLine();
        while ((str=f2.readLine())!=null){
            s[0]=str.substring(0, 20).trim();
            s[1]=str.substring(20,40).trim();
            s[2]=str.substring(40,60).trim();
            s[3]=str.substring(60,80).trim();
           this.addPerson(new Person(s[0],s[1],s[2]));
        }
        f2.close();
        f1.close();
    }
    public void displayPerson() throws IOException {
        String str;
        File f=new File("persons.txt");

        FileReader f1=new FileReader(f);
        BufferedReader f2=new BufferedReader(f1);
        while ((str=f2.readLine())!=null){
            System.out.println(str);
        }
        f2.close();
        f1.close();
    }
    public void writePerson() throws IOException {
        Person person;
        String str;
        File f = new File("persons.txt");
        FileWriter f1 = new FileWriter(f);
        BufferedWriter f2 = new BufferedWriter(f1);
        f2.write(String.format("%-20s%-20s%-20s%-20s", "姓名", "地址", "电话", "序号")); // 按指定宽度对齐
        f2.newLine();
        f2.close();
        f1.close();
        FileWriter f3 = new FileWriter("persons.txt", true);
        BufferedWriter f4 = new BufferedWriter(f3);
        for (int j = 0; j < this.persons.size(); j++) {
            person = this.persons.get(j);
            f4.write(String.format("%-20s%-20s%-20s%-20s", person.getName(), person.getAddress(), person.getNumber(), j + 1));
            f4.newLine();
        }
        f4.close();
        f3.close();
    }
}

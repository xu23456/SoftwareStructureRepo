/**
 Test_Name:TiXiJieGou2
 Date:2023.10.23
 Number:202131060927
 Name:Tang_Zhizhen
 **/
import java.io.IOException;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String str;
            Scanner scanner = new Scanner(System.in);
            LogicServer logicServer = new LogicServer();
            DataServer dataServer = new DataServer();
            System.out.println("手机通讯录管理系统");
            System.out.println("查看通讯录或退出？(y/n)");
            str= scanner.nextLine();
            if (str.equals("n")) {
                break;
            }
            System.out.println("通讯录:");
            dataServer.systemInit(logicServer);
            dataServer.displayPerson();
            System.out.println("请选择操作:");
            System.out.println("A.添加联系人\tB.删除联系人\tC.查找联系人\tD.修改联系人");
            str = scanner.nextLine();
            switch (str) {
                case "A":
                    System.out.println("请输入联系人姓名:");
                    String name = scanner.nextLine();
                    System.out.println("请输入联系人地址:");
                    String address = scanner.nextLine();
                    System.out.println("请输入联系人电话:");
                    String phoneNumber = scanner.nextLine();
                    Person newPerson = new Person(name, address, phoneNumber);
                    logicServer.addPerson(newPerson);
                    break;
                case "B":
                    System.out.println("请输入要删除的联系人id: ");
                    int indexToDelete = scanner.nextInt();
                    logicServer.deletePerson(indexToDelete - 1);
                    break;
                case "C":
                    System.out.println("请输入要查看的联系人id: ");
                    int indexToserch = scanner.nextInt();
                    System.out.println(logicServer.getPerson(indexToserch - 1));
                    break;
                case "D":
                    System.out.println("请输入要编辑的联系人id: ");
                    int indexToEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入新的姓名: ");
                    String newName = scanner.nextLine();
                    System.out.println("请输入新的地址: ");
                    String newAddress = scanner.nextLine();
                    System.out.println("请输入新的电话号码: ");
                    String newPhoneNumber = scanner.nextLine();
                    Person editedPerson = new Person(newName, newAddress, newPhoneNumber);
                    logicServer.editPerson(indexToEdit - 1, editedPerson);
                    break;
                default:
                    System.out.println("输入有误！");
            }
            dataServer.writePerson(logicServer);
            if (!str.equals("C")) {
                System.out.println("修改后的通讯录:");
                dataServer.displayPerson();
            }
        }
    }
}

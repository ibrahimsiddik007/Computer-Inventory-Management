package ShopManagementSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


class Motherboard implements Serializable {
    private String motherboardname;
    private String supported_ram;
    private String supported_processor;
    private String Slots;
    private double boardprice;


    Motherboard(String motherboardname, String supportedram, String supportedprocessor, String Slots, double boardprice) {
        setMotherboardname(motherboardname);
        setSlots(Slots);
        setSupportedprocessor(supportedprocessor);
        setSupportedram(supportedram);
        setBoardprice(boardprice);
    }

    public void setBoardprice(double boardprice) {
        this.boardprice = boardprice;
    }

    public double getBoardprice() {
        return this.boardprice;
    }

    public void setMotherboardname(String motherboardname) {
        this.motherboardname = motherboardname;
    }

    public String getMotherboardname() {
        return this.motherboardname;
    }

    public void setSlots(String slots) {
        this.Slots = slots;
    }

    public String getSlots() {
        return this.Slots;
    }

    public void setSupportedprocessor(String supported_processor) {
        this.supported_processor = supported_processor;
    }

    public String getSupportedprocessor() {
        return this.supported_processor;
    }

    public void setSupportedram(String supported_ram) {
        this.supported_ram = supported_ram;
    }
    public String getSupportedram() {
        return this.supported_ram;
    }

    public String toString() {
        return "Name: "+getMotherboardname()+"\nRam-supports: "+getSupportedram()+"\nProcessor supported: "+getSupportedprocessor()+"\nSlots: "+getSlots()+"\nPrice: "+getBoardprice()+"Taka";
    }

}


public class Project_Motherboard {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        ArrayList<Motherboard> mb=new ArrayList<>();

        File motherboard_file=new File("MotherBoard.txt");

        ListIterator mb_list_iterator=null;
        int choice;
        do{
            System.out.println("Enter the choice from below: \n1:Insert Motherboard\n2:Display Motherboard List\n3:Search Motherboard Specifically\n4:Remove A Motherboard\n5:Update A Motherboard Information\n0:Exit");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:
                {
                    try {
                      ObjectInputStream  oinp=new ObjectInputStream(new FileInputStream(motherboard_file));
                        mb =(ArrayList<Motherboard>)oinp.readObject(); //mb = motherboard arraylist.
                        //typecasted to Arraylist so that the object reader can collect data as an object...
                    } catch (Exception e) {

                    }

                    System.out.print("How many motherboard do you want to enter:");
                    int quantity_loop=in.nextInt();
                    int count = 1;
                    for(int i=0;i<quantity_loop;i++)
                    {
                        System.out.println("Number : " +count);
                        System.out.print("Enter Name & Model:");
                        String boardname=in.next();
                        in.nextLine();
                        System.out.print("Enter Ram Supports: ");
                        String boardram=in.next();
                        in.nextLine();
                        System.out.print("Enter Processor Supports:");
                        String boardprocessor =in.next();
                        in.nextLine();
                        System.out.print("Enter Slot Details:");
                        String boardslots = in.next();
                        in.nextLine();
                        System.out.print("Enter Price:");
                        double boardprices= in.nextDouble();
                        mb.add(new Motherboard(boardname, boardram, boardprocessor, boardslots, boardprices));
                        count++;
                         //mb = motherboard arraylist...
                    }
                    ObjectOutputStream ooutp =new ObjectOutputStream(new FileOutputStream(motherboard_file));
                    ooutp.writeObject(mb);
                    ooutp.close();
                    break;
                }


                case 2:
                {
                    try {
                       ObjectInputStream oinp=new ObjectInputStream(new FileInputStream(motherboard_file));
                       mb=(ArrayList<Motherboard>)oinp.readObject(); //mb = motherboard arraylist...
                    } catch (Exception e) {

                    }
                    mb_list_iterator=mb.listIterator(); //here the cursor position will traverse through the object and pick the files...
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
                    int count = 1;
                    while(mb_list_iterator.hasNext())
                    {
                        System.out.println("Number "+count);
                        System.out.println("_____________________");
                        System.out.println(mb_list_iterator.next());
                        System.out.println("______________________");
                        count++;
                    }
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
                    break;
                }
                case 3:
                {   boolean search_result=false;
                    try {
                        ObjectInputStream oinp=new ObjectInputStream(new FileInputStream(motherboard_file));
                        mb=(ArrayList<Motherboard>)oinp.readObject();
                        //mb= motherboard
                       //typecasted to Arraylist so that the object reader can collect data as an object...

                    } catch (Exception e) {

                    }
                    mb_list_iterator=mb.listIterator();
                    System.out.print("Enter Name To Search: ");
                    String searchboard= in.next();
                    while(mb_list_iterator.hasNext())
                    {
                        Motherboard boardobj = (Motherboard) mb_list_iterator.next();
                        if(boardobj.getMotherboardname().equalsIgnoreCase(searchboard))
                        {
                            System.out.println(boardobj);
                            search_result = true; //the search result is converted to true.
                        }
                    }
                    if(!search_result)  //if search_result is not found then...
                    {
                        System.out.println("Product Not Found....");
                        System.out.println();
                    }
                    break;
                }

                case 4:
                {   boolean searchresult = false;
                    try {
                        ObjectInputStream oinp =new ObjectInputStream(new FileInputStream(motherboard_file));
                        mb=(ArrayList<Motherboard>)oinp.readObject();
                    } catch (Exception e) {

                    }
                    mb_list_iterator=mb.listIterator();
                    System.out.print("Enter Name To Delete:");
                    String deleteboard = in.next();

                    while(mb_list_iterator.hasNext())
                    {
                        Motherboard boardobj2 = (Motherboard) mb_list_iterator.next();
                        if(boardobj2.getMotherboardname().equalsIgnoreCase(deleteboard)) {
                            mb_list_iterator.remove(); //removing that object found by the list iterator..
                            searchresult = true;//changing the search result to true..
                        }
                    }
                    if(searchresult)
                    {          ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(motherboard_file));
                        ooutp.writeObject(mb); // mb = motherboard arraylist
                        ooutp.close();
                        System.out.println("Record Deleted");
                        System.out.println();
                    }
                    else{
                        System.out.println("Item Not Found..");
                        System.out.println();
                    }
                    break;

                }
                case 5:
                {
                    boolean searchresult = false;
                    try {
                        ObjectInputStream oinp=new ObjectInputStream(new FileInputStream(motherboard_file));
                        mb =(ArrayList<Motherboard>)oinp.readObject();
                    } catch (Exception e) {

                    }
                    mb_list_iterator = mb.listIterator();
                    System.out.print("Enter Name to Update:");
                    String updateboard = in.next();
                    while(mb_list_iterator.hasNext())
                    {
                        Motherboard boardobj2=(Motherboard)mb_list_iterator.next();
                        if(boardobj2.getMotherboardname().equalsIgnoreCase(updateboard))
                        {
                            System.out.print("Enter New Name & Model:");
                            String newboardname = in.nextLine();
                            in.nextLine();
                            System.out.print("Enter New Ram Supports");
                            String newboardram= in.nextLine();
                            in.nextLine();
                            System.out.println("Enter New Processor Supports:");
                            String newboardprocessor= in.nextLine();
                            in.nextLine();
                            System.out.print("Enter New Slot Details:");
                            String newboardslots= in.nextLine();
                            in.nextLine();
                            System.out.print("Enter New Price:");
                            double newboardprices= in.nextDouble();

                            Motherboard boardobj = new Motherboard(newboardname, newboardram, newboardprocessor, newboardslots, newboardprices);

                            mb_list_iterator.set(boardobj);


                            searchresult = true;
                        }
                    }
                    if(searchresult) //if search result is true...
                    {
                       ObjectOutputStream ooutp =new ObjectOutputStream(new FileOutputStream(motherboard_file));
                        ooutp.writeObject(motherboard_file);
                        ooutp.close();
                        System.out.println("Record Updated");
                        System.out.println();
                    }
                }
            }

        }while(choice!=0);
    }
}





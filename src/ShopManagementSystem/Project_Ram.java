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


class Ram implements Serializable{
    private String ramname;
    private int busspeed;
    private int ramsize;
    private String type;
    private int ramprice;
    Ram()
    {

    }
    public Ram(String ramname,int busspeed,int ramsize,String type,int ramprice)
    {
        setBusspeed(busspeed);
        setRamname(ramname);
        setRamprice(ramprice);
        setRamsize(ramsize);
        setType(type);
    }
    public void setBusspeed(int busspeed) {
        this.busspeed = busspeed;
    }
    public void setRamname(String ramname) {
        this.ramname = ramname;
    }
    public void setRamprice(int ramprice) {
        this.ramprice = ramprice;
    }
    public void setRamsize(int ramsize) {
        this.ramsize = ramsize;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getBusspeed() {
        return busspeed;
    }
    public String getRamname() {
        return ramname;
    }
    public int getRamprice() {
        return ramprice;
    }
    public int getRamsize() {
        return ramsize;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return "\nName: "+getRamname()+"\nBusspeed: "+getBusspeed()+"\nRam-size: "+getRamsize()+" GB"+"\nRam-type: "+getType()+"\nPrice: "+getRamprice();
    }
}
public class Project_Ram {
    public static void main(String[] args) throws Exception {
        File ram_file=new File("Ram.txt");
        Scanner in=new Scanner(System.in);

        ArrayList<Ram> ram_arraylist=new ArrayList<>();
        ListIterator ram_list_iterator;

        int choice;
        do{

            System.out.println("Enter choice\n1:Insert A RAM\n2:Display All The Ram List\n3:Search A Specific RAM\n4:Delete A RAM\n5:Update A Ram\n0:Exit");
            choice=in.nextInt();
            switch(choice){
                case 1:
                {   try {
                   ObjectInputStream inp=new ObjectInputStream(new FileInputStream(ram_file));
                    ram_arraylist=(ArrayList<Ram>)inp.readObject();
                } catch (Exception e) {

                }
                    System.out.print("How Many Product Do You Want To Enter:");
                    int quantity=in.nextInt();
                    int count = 1;
                    for(int i=0;i<quantity;i++){
                        System.out.println("Number "+count);

                        System.out.print("Enter name:");
                        String ramname=in.next();
                        in.nextLine();
                        System.out.print("Enter Bus-speed:");
                        int busspeed=in.nextInt();
                        System.out.print("Enter Ram-Size:");
                        int ramsize=in.nextInt();
                        in.nextLine();
                        System.out.print("Enter Ram-type:");
                        String type=in.next();
                        in.nextLine();
                        System.out.print("Enter Price:");
                        int ramprice=in.nextInt();
                        ram_arraylist.add(new Ram(ramname, busspeed, ramsize, type, ramprice));
                        count++;
                    }
                    ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(ram_file));
                    ooutp.writeObject(ram_arraylist);
                    ooutp.close();

                    break;
                }

                case 2:
                {
                    try {
                    ObjectInputStream inp=new ObjectInputStream(new FileInputStream(ram_file));
                       ram_arraylist=(ArrayList<Ram>)inp.readObject();
                } catch (Exception e) {

                }
                    ram_list_iterator=ram_arraylist.listIterator();
                    System.out.println("------------------------------");
                    int count = 1;
                    while(ram_list_iterator.hasNext())
                    {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Number "+count);
                        System.out.println(ram_list_iterator.next());
                        count++;
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    }
                    System.out.println("------------------------------");
                    break;

                }

                case 3:

                {  boolean searchram=false;
                    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(ram_file));
                        ram_arraylist=(ArrayList<Ram>)inp.readObject();
                    } catch (Exception e) {

                    }
                    ram_list_iterator=ram_arraylist.listIterator();
                    System.out.print("Enter Name To Search:");
                    String ramsearch=in.next();



                    while(ram_list_iterator.hasNext())

                    {
                        Ram r1=(Ram)ram_list_iterator.next();
                        if(r1.getRamname().equalsIgnoreCase(ramsearch)){
                            System.out.println("------------------------------");
                            System.out.println(r1);

                            System.out.println("------------------------------");
                            searchram=true;
                        }
                    }

                    if(!searchram)
                    {
                        System.out.println("Not Found.....");
                    }

                    break;
                }
                case 4:{


                    boolean searchram=false;
                    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(ram_file));
                        ram_arraylist=(ArrayList<Ram>)inp.readObject();
                    } catch (Exception e) {

                    }
                    ram_list_iterator=ram_arraylist.listIterator();
                    System.out.print("Enter Name to Delete:");
                    String deleteram=in.next();


                    while(ram_list_iterator.hasNext())
                    {
                        Ram r2=(Ram) ram_list_iterator.next();
                        if(r2.getRamname().equalsIgnoreCase(deleteram)){
                            System.out.println(r2);
                            ram_list_iterator.remove();
                            searchram=true;
                        }
                    }
                    if(searchram) //if it is true
                    {
                        ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(ram_file));
                        ooutp.writeObject(ram_arraylist);
                        ooutp.close();
                        System.out.println("Record deleted.....");
                        System.out.println();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
                    }
                    else{
                        System.out.println("NOT found....");
                    }

                    break;

                }
                case 5:
                {
                    boolean searchram=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(ram_file));
                        ram_arraylist=(ArrayList<Ram>)inp.readObject();
                    } catch (Exception e) {

                    }
                    ram_list_iterator=ram_arraylist.listIterator();
                    System.out.print("Enter Name to Update:");
                    String updateram=in.next();

                    while(ram_list_iterator.hasNext())
                    {
                        Ram r3=(Ram)ram_list_iterator.next();
                        if(r3.getRamname().equalsIgnoreCase(updateram))
                        {
                            System.out.println(r3);
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.print("Enter New Name:");
                            String newramname=in.next();
                            in.nextLine();
                            System.out.print("Enter New Bus-speed:");
                            int newbusspeed=in.nextInt();
                            System.out.print("Enter New Ram-Size:");
                            int newramsize=in.nextInt();
                            in.nextLine();
                            System.out.print("Enter New Ram-type:");
                            String newtype=in.next();
                            in.nextLine();
                            System.out.print("Enter New Price:");
                            int newramprice=in.nextInt();


                            Ram vram=new Ram();

                            vram=r3; //assigning a variable to the object..
                            vram.setRamname(newramname);
                            vram.setBusspeed(newbusspeed);
                            vram.setRamprice(newramprice);
                            vram.setRamsize(newramsize);
                            vram.setType(newtype);
                            ram_list_iterator.set(vram);

                            searchram=true;
                        }
                    }
                    if(searchram)  //if it is true...
                    {
                        ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(ram_file));
                        ooutp.writeObject(ram_arraylist);
                        ooutp.close();
                        System.out.println("Record updated.....");
                    }else{
                        System.out.println("NOT found....");
                    }

                    break;
                }
            }
        }while(choice!=0);

    }
}

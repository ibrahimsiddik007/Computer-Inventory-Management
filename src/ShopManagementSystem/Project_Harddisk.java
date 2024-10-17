package ShopManagementSystem;

import java.util.ListIterator;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Harddisk implements Serializable{
    private String Diskname;
    private String Disktype;
    private int Diskspeed;
    private int Disksize;
    private int Diskprice;

    public Harddisk(String Diskname,String Disktype,int Diskspeed,int Disksize,int Diskprice)
    {
        this.Diskname=Diskname;
        this.Diskprice=Diskprice;
        this.Disksize=Disksize;
        this.Diskspeed=Diskspeed;
        this.Disktype=Disktype;
    }
    public void setDiskname(String Diskname) {
        this.Diskname = Diskname;
    }
    public void setDiskprice(int Diskprice) {
        this.Diskprice = Diskprice;
    }
    public void setDisksize(int Disksize) {
        this.Disksize = Disksize;
    }
    public void setDiskspeed(int Diskspeed) {
        this.Diskspeed = Diskspeed;
    }
    public void setDisktype(String Disktype) {
        this.Disktype = Disktype;
    }
    public String getDiskname() {
        return this.Diskname;
    }
    public int getDiskprice() {
        return this.Diskprice;
    }
    public int getDisksize() {
        return this.Disksize;
    }
    public int getDiskspeed() {
        return this.Diskspeed;
    }
    public String getDisktype() {
        return this.Disktype;
    }
    @Override
    public String toString() {
        return "Name: "+getDiskname()+" "+"\nType: "+getDisktype()+" "+"\nSize :"+getDisksize()+"GB"+" "+"\nSpeed :"+getDiskspeed()+"Mbps"+"\nPrice :"+getDiskprice()+"tk";
    }

}
public class Project_Harddisk {
    public static void main(String[] args) throws Exception {

        File hfile=new File("Harddisk.txt");
        Scanner in=new Scanner(System.in);
        ObjectInputStream inpstr;
        ObjectOutputStream outstr;

        ArrayList<Harddisk> hddarrlist =new ArrayList<>();
        ListIterator<Harddisk> itor=null;
        int choice;
        do{
            System.out.println("Enter choice\n1:Insert HardDisk\n2:Display All List\n3:Search An Individual Disk\n4:Delete An Item\n5:Update Disk Information.\n0:exit");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:{
                    try {
                        inpstr=new ObjectInputStream(new FileInputStream(hfile));
                        hddarrlist=(ArrayList<Harddisk>)inpstr.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }

                    System.out.print("How many:");
                    int Howmany=in.nextInt();
                    for(int i=0;i<Howmany;i++)
                    {
                        System.out.print("Enter name:");
                        String Diskname=in.next();
                        in.nextLine();
                        System.out.print("Enter type(SSD/HDD):");
                        String Disktype=in.next();
                        in.nextLine();
                        System.out.print("Enter Size:");
                        int Disksize=in.nextInt();
                        System.out.print("Enter Speed:");
                        int Diskspeed=in.nextInt();
                        System.out.print("Enter Price:");
                        int Diskprice=in.nextInt();
                        hddarrlist.add(new Harddisk(Diskname, Disktype, Diskspeed, Disksize, Diskprice));
                    }
                    outstr=new ObjectOutputStream(new FileOutputStream(hfile));
                    outstr.writeObject(hddarrlist);
                    outstr.close();
                    break;
                }
                case 2:
                {   try {
                    inpstr=new ObjectInputStream(new FileInputStream(hfile));
                    hddarrlist=(ArrayList<Harddisk>)inpstr.readObject();
                } catch (Exception e) {

                }
                    itor=hddarrlist.listIterator();
                    System.out.println("_____________________________________________________________");
                    while(itor.hasNext())
                    {
                        System.out.println(itor.next());
                    }
                    System.out.println("_____________________________________________________________");
                    break;
                }
                case 3:
                {   boolean f=false;
                    try {
                        inpstr=new ObjectInputStream(new FileInputStream(hfile));
                        hddarrlist=(ArrayList<Harddisk>)inpstr.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    itor=hddarrlist.listIterator();
                    System.out.println("Enter Name Search:");
                    String searchdisk=in.next();

                    while(itor.hasNext())
                    {
                        Harddisk h1 = itor.next();
                        if(h1.getDiskname().equalsIgnoreCase(searchdisk))
                        {
                            System.out.println("_____________________________________________________________");
                            System.out.println(h1);
                            System.out.println("_____________________________________________________________");

                            f=true;
                        }
                    }
                    if(!f)  //if f is false..
                    {
                        System.out.println("Not Found...");
                        System.out.println();
                    }

                    break;
                }
                case 4:
                {
                    boolean f2=false;
                    try {
                        inpstr=new ObjectInputStream(new FileInputStream(hfile));
                        hddarrlist=(ArrayList<Harddisk>)inpstr.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    itor=hddarrlist.listIterator();
                    System.out.println("Enter the name to delete:");
                    String deleteddisk=in.next();

                    while(itor.hasNext())
                    {
                        Harddisk h2 = (Harddisk) itor.next();
                        if(h2.getDiskname().equalsIgnoreCase(deleteddisk))
                        {
                            System.out.println("_____________________________________________________________");
                            System.out.println(h2);
                            System.out.println("_____________________________________________________________");
                            itor.remove();

                            f2=true;
                        }
                    }
                    if(f2)  //if f2 is true
                    {
                        outstr=new ObjectOutputStream(new FileOutputStream(hfile));
                        outstr.writeObject(hddarrlist);
                        outstr.close();
                        System.out.println("Item deleted...");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not found...");
                    }

                    break;

                }

                case 5:
                {
                    boolean f3=false;
                    try {
                        inpstr=new ObjectInputStream(new FileInputStream(hfile));
                        hddarrlist=(ArrayList<Harddisk>)inpstr.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    itor=hddarrlist.listIterator();
                    System.out.println("Enter Name To Update:");
                    String updatedisk=in.next();

                    while(itor.hasNext())
                    {
                        Harddisk h3= itor.next();
                        if(h3.getDiskname().equalsIgnoreCase(updatedisk))
                        {
                            System.out.println("_____________________________________________________________");
                            System.out.println(h3);
                            System.out.println("_____________________________________________________________");
                            in.nextLine();
                            System.out.print("Enter new name:");
                            String Diskname=in.next();
                            in.nextLine();
                            System.out.print("Enter new type(SSD/HDD):");
                            String Disktype=in.next();
                            in.nextLine();
                            System.out.print("Enter New Size:");
                            int Disksize=in.nextInt();
                            System.out.print("Enter New Speed:");
                            int Diskspeed=in.nextInt();
                            System.out.print("Enter new Price:");
                            int Diskprice=in.nextInt();
                            h3=new Harddisk(Diskname, Disktype, Diskspeed, Disksize, Diskprice);
                            itor.set(h3);

                            f3=true;
                        }
                    }
                    if(f3)
                    {     outstr=new ObjectOutputStream(new FileOutputStream(hfile));
                        outstr.writeObject(hddarrlist);
                        outstr.close();
                        System.out.println("Record updated...");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not found...");
                    }

                    break;
                }
            }
        }while(choice!=0);



    }
}
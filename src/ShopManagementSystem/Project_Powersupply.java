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

class Powersupply implements Serializable{
    private String supplyname;
    private int watt;
    private double dcoutput;
    private int supplyPrice;


    public Powersupply(String supplyname,int watt,double dcoutput,int supplyPrice)
    {
        this.dcoutput=dcoutput;
        this.supplyPrice=supplyPrice;
        this.supplyname=supplyname;
        this.watt=watt;
    }
    public void setDcoutput(double dcoutput) {
        this.dcoutput = dcoutput;
    }
    public void setSupplyPrice(int supplyPrice) {
        this.supplyPrice = supplyPrice;
    }
    public void setSupplyname(String supplyname) {
        this.supplyname = supplyname;
    }
    public void setWatt(int watt) {
        this.watt = watt;
    }
    public double getDcoutput() {
        return this.dcoutput;
    }
    public int getSupplyPrice() {
        return this.supplyPrice;
    }
    public String getSupplyname() {
        return this.supplyname;
    }
    public int getWatt() {
        return this.watt;
    }
    @Override
    public String toString() {
        return "Name: "+getSupplyname()+"\nPower: "+getWatt()+" watt"+"\nDC-Output:"+getDcoutput()+"V"+"\nPrice:"+getSupplyPrice()+"tk";
    }
}
public class Project_Powersupply {
    public static void main(String[] args) throws Exception {
        File supply=new File("PowerSupply.txt");
        ArrayList<Powersupply> supplylist=new ArrayList<>();
        Scanner in=new Scanner(System.in);
        ObjectOutputStream powerout=null;
        ObjectInputStream powerin=null;
        ListIterator powerlist=null;
        int choice;
        do{
            System.out.println("Enter choice\n1:Insert Power Supply\n2:Display All List\n3:Search A File\n4:Delete A File\n5:Update A File\n0:Exit");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:
                {   try {
                    powerin=new ObjectInputStream(new FileInputStream(supply));
                    supplylist=(ArrayList<Powersupply>)powerin.readObject();
                } catch (Exception e) {
                    //TODO: handle exception
                }
                    System.out.print("How Many:");
                    int Howmany=in.nextInt();
                    int count  = 1;
                    for(int v=0;v<Howmany;v++){
                        System.out.println("Number : "+count);
                        System.out.print("Enter Name:");
                        String supplyname=in.next();
                        in.nextLine();
                        System.out.print("Enter Watt-power:");
                        int watt=in.nextInt();
                        System.out.print("Enter Dc-outputs:");
                        double dcoutput=in.nextDouble();
                        System.out.print("Enter Price:");
                        int supplyPrice=in.nextInt();
                        supplylist.add(new Powersupply(supplyname, watt, dcoutput, supplyPrice));
                        count++;
                    }
                    powerout=new ObjectOutputStream(new FileOutputStream(supply));
                    powerout.writeObject(supplylist);
                    powerout.close();

                    System.out.println("Record Updated..");
                    System.out.println();
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");

                    break;
                }
                case 2:
                {

                    try {
                        powerin=new ObjectInputStream(new FileInputStream(supply));
                        supplylist=(ArrayList<Powersupply>)powerin.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    powerlist=supplylist.listIterator();
                    System.out.println("________________________________________________");
                    int count = 1;
                    while(powerlist.hasNext())
                    {
                        System.out.println("Number : "+count);
                        System.out.println(powerlist.next());
                        System.out.println();
                        count++;
                    }
                    System.out.println("________________________________________________");
                    break;
                }
                case 3:
                {   boolean found40=false;
                    try {
                        powerin=new ObjectInputStream(new FileInputStream(supply));
                        supplylist=(ArrayList<Powersupply>)powerin.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    powerlist=supplylist.listIterator();
                    System.out.print("Enter Name to Search:");
                    String scrsupply=in.next();
                    while(powerlist.hasNext())
                    {   Powersupply p1=(Powersupply)powerlist.next();
                        if(p1.getSupplyname().equalsIgnoreCase(scrsupply))
                        {    System.out.println("________________________________________________");
                            System.out.println(p1);
                            System.out.println("________________________________________________");
                            found40=true;
                        }
                    }
                    if(!found40)
                    {
                        System.out.println("Not found.....");
                        System.out.println();
                    }



                    break;
                }
                case 4:
                {
                    boolean found41=false;
                    try {
                        powerin=new ObjectInputStream(new FileInputStream(supply));
                        supplylist=(ArrayList<Powersupply>)powerin.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    powerlist=supplylist.listIterator();
                    System.out.print("Enter name to Search:");
                    String deletesupply=in.next();
                    while(powerlist.hasNext())
                    {   Powersupply p2=(Powersupply)powerlist.next();
                        if(p2.getSupplyname().equalsIgnoreCase(deletesupply))
                        {   System.out.println("________________________________________________");
                            System.out.println(p2);
                            System.out.println("________________________________________________");
                            powerlist.remove();
                            found41=true;
                        }
                    }
                    if(found41)
                    {    powerout=new ObjectOutputStream(new FileOutputStream(supply));
                        powerout.writeObject(supplylist);
                        powerout.close();

                        System.out.println("Record deleted.....");
                        System.out.println();
                    }else{
                        System.out.println("Not found.....");
                    }



                    break;
                }
                case 5:
                {
                    boolean found42=false;
                    try {
                        powerin=new ObjectInputStream(new FileInputStream(supply));
                        supplylist=(ArrayList<Powersupply>)powerin.readObject();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    powerlist=supplylist.listIterator();
                    System.out.print("Enter name to Search:");
                    String updatesupply=in.next();
                    while(powerlist.hasNext())
                    {   Powersupply p3=(Powersupply)powerlist.next();
                        if(p3.getSupplyname().equalsIgnoreCase(updatesupply))
                        {
                            System.out.println("________________________________________________");
                            System.out.println(p3);
                            System.out.println("________________________________________________");
                            System.out.println();

                            System.out.print("Enter new name:");
                            String newsupplyname=in.next();
                            System.out.print("Enter new Watt-power:");
                            int newwatt=in.nextInt();
                            System.out.print("Enter new Dc-outputs:");
                            double newdcoutput=in.nextDouble();
                            System.out.print("Enter new Price:");
                            int newsupplyPrice=in.nextInt();

                            p3=new Powersupply(newsupplyname, newwatt, newdcoutput, newsupplyPrice);
                            powerlist.set(p3);

                            found42=true;
                        }
                    }
                    if(found42)
                    {    powerout=new ObjectOutputStream(new FileOutputStream(supply));
                        powerout.writeObject(supplylist);
                        powerout.close();

                        System.out.println("Record deleted.....");
                        System.out.println();
                    }else{
                        System.out.println("Not found.....");
                    }



                    break;
                }
            }
        }while(choice!=0);

    }
}
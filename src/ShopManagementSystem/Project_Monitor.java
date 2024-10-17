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


class Monitor implements Serializable{
    private String brand;
    private int msize;  //monitor size
    private int refresh;
    private int brightness;
    private int mprice;  //monitor price
    public Monitor(String brand, int msize, int refresh, int brightness, int mprice)
    {
        this.brand=brand;
        this.msize=msize;
        this.refresh=refresh;
        this.brightness = brightness;
        this.mprice=mprice;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setMsize(int msize) {
        this.msize = msize;
    }
    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    public void setMprice(int mprice) {
        this.mprice = mprice;
    }

    public String getBrand() {
        return brand;
    }
    public int getMsize() {
        return msize;
    }
    public int getRefresh() {
        return refresh;
    }
    public int getBrightness() {
        return brightness;
    }
    public int getMprice() {
        return mprice;
    }
    public String toString()
    {
        return "Name: "+getBrand()+"\nSize: "+getMsize()+"inch"+"\nRefreshrate: "+getRefresh()+"HZ"+"\nBrightness: "+ getBrightness()+"nits"+"\nPrice :"+getMprice()+"TK";
    }



}
public class Project_Monitor {
    public static void main(String[] args) throws Exception {
        int choice;
        File monitor_file =new File("Monitor.txt");
        ArrayList<Monitor> monitor_arraylist=new ArrayList<>();
        Scanner in=new Scanner(System.in);

        ListIterator monitor_list_iterator;
        do{
            System.out.println("Enter choice\n1:Insert A Monitor\n2:Display All List Of Monitors\n3:Search A Monitor\n4:Delete A Monitor\n5:Update A Monitor\n0:Exit");
            choice=in.nextInt();
            switch(choice){
                case 1:
                {
                    try {

                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(monitor_file));
                        monitor_arraylist=(ArrayList<Monitor>)inp.readObject();

                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    System.out.print("How Many Do You Want To Enter: :");
                    int quantity_choice=in.nextInt();
                    int count = 1;
                    for(int i=0;i<quantity_choice;i++)
                    {
                        System.out.println("Number : "+count);
                        System.out.print("Enter Brand:");
                        String monitorname=in.next();
                        in.nextLine();
                        System.out.print("Enter Screen Size:");
                        int screensize=in.nextInt();
                        System.out.print("Enter Refresh rate:");
                        int monitorrefresh=in.nextInt();
                        System.out.print("Enter Brightness:");
                        int monitorbrithness=in.nextInt();
                        System.out.print("Enter Price:");
                        int monitorprice=in.nextInt();

                        monitor_arraylist.add(new Monitor(monitorname, screensize, monitorrefresh, monitorbrithness, monitorprice));
                        count++;

                    }
                    ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(monitor_file ));
                    ooutp.writeObject(monitor_arraylist);
                    ooutp.close();
                }
                break;

                case 2:
                {
                    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(monitor_file ));
                        monitor_arraylist=(ArrayList<Monitor>)inp.readObject();

                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    monitor_list_iterator=monitor_arraylist.listIterator();
                    System.out.println("----------------------");
                    int count = 1;
                    while(monitor_list_iterator.hasNext())
                    {
                        System.out.println("Number : "+count);
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println(monitor_list_iterator.next());
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        count++;
                    }
                    System.out.println("----------------------");
                    break;
                }
                case 3:
                {  boolean monitorfound=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(monitor_file ));
                        monitor_arraylist=(ArrayList<Monitor>)inp.readObject();

                    } catch (Exception e) {

                    }
                    monitor_list_iterator=monitor_arraylist.listIterator();
                    System.out.print("Enter Name to Search:");
                    String searchmonitor=in.next();
                    while(monitor_list_iterator.hasNext())
                    {   Monitor m=(Monitor) monitor_list_iterator.next();
                        if(m.getBrand().equalsIgnoreCase(searchmonitor))
                        {
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println(m);
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println();
                            monitorfound=true;
                        }
                    }
                    if(!monitorfound)  //if not found...
                    {
                        System.out.println("Not found.....");
                    }
                    break;
                }
                case 4:
                {    boolean monitorfound=false;
                    try
                    {

                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(monitor_file ));
                        monitor_arraylist=(ArrayList<Monitor>)inp.readObject();

                    } catch (Exception e) {

                    }
                    monitor_list_iterator=monitor_arraylist.listIterator();
                    System.out.print("Enter Name to Search:");
                    String deletemoniter=in.next();
                    while(monitor_list_iterator.hasNext())
                    {
                        Monitor m2=(Monitor)monitor_list_iterator.next();
                        if(m2.getBrand().equalsIgnoreCase(deletemoniter))
                        {

                            monitor_list_iterator.remove();
                            monitorfound=true;
                        }
                    }
                    if(monitorfound) //if it is true...
                    {
                        ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(monitor_file ));
                        ooutp.writeObject(monitor_arraylist);
                        ooutp.close();
                        System.out.println("Item Deleted..");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not Found...");
                    }
                    break;
                }
                case 5:
                {   boolean monitor_found=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(monitor_file ));
                        monitor_arraylist=(ArrayList<Monitor>)inp.readObject();

                    } catch (Exception e) {

                    }
                    monitor_list_iterator=monitor_arraylist.listIterator();
                    System.out.print("Enter Name to Update:");
                    String updatemoniter=in.next();
                    while(monitor_list_iterator.hasNext())
                    {
                        Monitor m3=(Monitor)monitor_list_iterator.next();
                        if(m3.getBrand().equalsIgnoreCase(updatemoniter))
                        {
                            System.out.println();
                            System.out.println(m3);
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>");
                            System.out.print("Enter New Name:");
                            String newname=in.next();
                            in.nextLine();
                            System.out.print("Enter New Size:");
                            int newsize=in.nextInt();
                            System.out.print("Enter New RefreshRate:");
                            int newref=in.nextInt();
                            System.out.print("Enter new Brightness:");
                            int newbrightness=in.nextInt();
                            System.out.print("Enter new price:");
                            int newmprice=in.nextInt();

                          m3 = new Monitor(newname,newsize,newref,newbrightness,newmprice);


                            monitor_list_iterator.set(m3);


                            monitor_found=true;
                        }
                    }
                    if(monitor_found){
                        ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(monitor_file ));
                        ooutp.writeObject(monitor_arraylist);
                        ooutp.close();
                        System.out.println("Record Updated....");
                        System.out.println();
                    }else{
                        System.out.println("Not found");
                    }

                    break;
                }
            }

        }while(choice!=0);
    }
}

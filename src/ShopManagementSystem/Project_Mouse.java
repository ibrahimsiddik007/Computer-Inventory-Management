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

class Mouse implements Serializable{
    private String mousename;
    private double mouseprice;

    public Mouse(String mousename,double mouseprice ) {
        setMousename(mousename);
        setMouseprice(mouseprice);
    }

    public void setMousename(String mousename) {
        this.mousename = mousename;
    }
    public void setMouseprice(double mouseprice) {
        this.mouseprice = mouseprice;
    }
    public String getMousename() {
        return this.mousename;
    }
    public double getMouseprice() {
        return this.mouseprice;
    }
    @Override
    public String toString() {
        return "Name:"+getMousename()+"\nPrice:"+getMouseprice()+"tk";
    }
}



public class Project_Mouse {
    public static void main(String[] args) throws Exception{
        File mouse_file=new File("Mouse.txt");
        Scanner in=new Scanner(System.in);

        ArrayList<Mouse> mousearray=new ArrayList<Mouse>();
        ListIterator mouseIterator;
        int choice;
        do{
            System.out.println("Enter Choice\n1:Insert Mouse\n2:Display All The Mouse List\n3:Search A Mouse\n4:Delete A Mouse\n5:Update A Mouse\n0:Exit");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:
                {
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(mouse_file));
                        mousearray=(ArrayList<Mouse>)inp.readObject();
                    } catch (Exception e) {

                    }
                    System.out.print("How many mouse do you want to enter in the shop:");
                    int mousechoice=in.nextInt();
                    for(int i=0;i<mousechoice;i++)
                    {
                        System.out.println("Number "+i+":");
                        System.out.print("Enter name: ");
                        String mousename=in.next();
                        in.nextLine();
                        System.out.print("Enter price:");
                        double mouseprice=in.nextDouble();
                        mousearray.add(new Mouse(mousename, mouseprice));
                    }
                    ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(mouse_file));
                    ooutp.writeObject(mousearray);
                    ooutp.close();

                    break;
                }
                case 2:
                {
                    try {
                   ObjectInputStream inp=new ObjectInputStream(new FileInputStream(mouse_file));
                    mousearray=(ArrayList<Mouse>)inp.readObject();
                } catch (Exception e) {

                }
                    mouseIterator=mousearray.listIterator();
                    int count = 1;
                    while(mouseIterator.hasNext())
                    {
                        System.out.println("Number "+count);
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println(mouseIterator.next());
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
                        count++;

                    }
                    break;
                }

                case 3:
                {    boolean searchlist=false;
                    {
                        try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(mouse_file));
                        mousearray=(ArrayList<Mouse>)inp.readObject();
                    } catch (Exception e) {

                    }
                        mouseIterator=mousearray.listIterator();
                        System.out.print("Enter name to search:");
                        String searchmouse=in.nextLine();
                        while(mouseIterator.hasNext())
                        {
                            Mouse m1=(Mouse)mouseIterator.next();
                            if(m1.getMousename().equalsIgnoreCase(searchmouse))
                            {            System.out.println("---------------------------");
                                System.out.println(m1);
                                System.out.println("---------------------------");
                                searchlist=true;

                            }
                        }
                        if(!searchlist) //if not found...
                        {
                            System.out.println("Product Not found..");
                            System.out.println();
                        }
                        break;
                    }
                }
                case 4:
                {
                    boolean searchmouse=false;
                    {    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(mouse_file));
                        mousearray=(ArrayList<Mouse>)inp.readObject();
                    } catch (Exception e) {

                    }
                        mouseIterator=mousearray.listIterator();
                        System.out.print("Enter Name to delete:");
                        String deletemouse=in.nextLine();
                        while(mouseIterator.hasNext())
                        {
                            Mouse m2=(Mouse)mouseIterator.next();
                            if(m2.getMousename().equalsIgnoreCase(deletemouse))
                            {            System.out.println("---------------------------");
                                System.out.println(m2);
                                System.out.println("---------------------------");
                                mouseIterator.remove();
                                searchmouse=true;

                            }
                        }
                        if(searchmouse)  //if file is found....
                        {
                            ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(mouse_file));
                            ooutp.writeObject(mousearray);
                            ooutp.close();
                            System.out.println("Record deleted...");
                            System.out.println();
                        }
                        else{
                            System.out.println("File not found...");
                        }
                        break;
                    }
                }
                case 5:
                {

                    boolean searchmouse=false;
                    {
                        try {

                       ObjectInputStream  inp=new ObjectInputStream(new FileInputStream(mouse_file));
                        mousearray=(ArrayList<Mouse>)inp.readObject();
                    } catch (Exception e) {

                    }
                        mouseIterator=mousearray.listIterator();
                        System.out.print("Enter Name to Update:");
                        String deletemouse=in.nextLine();
                        while(mouseIterator.hasNext())
                        {
                            Mouse m3=(Mouse)mouseIterator.next();
                            if(m3.getMousename().equalsIgnoreCase(deletemouse))
                            {            System.out.println("---------------------------");
                                System.out.println(m3);
                                System.out.println("---------------------------");
                                System.out.print("Enter New Name:");
                                String newmousename=in.nextLine();
                                System.out.print("Enter New Price:");
                                double newmouseprice=in.nextDouble();
                                m3=new Mouse(newmousename, newmouseprice);
                                mouseIterator.set(m3);

                                searchmouse=true;

                            }
                        }
                        if(searchmouse)
                        {
                            ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream(mouse_file));
                            outp.writeObject(mousearray);
                            outp.close();
                            System.out.println("Record updated...");
                            System.out.println();
                        }
                        else{
                            System.out.println("Not Found...");
                        }
                        break;
                    }
                }
            }
        }while(choice!=0);




    }
}

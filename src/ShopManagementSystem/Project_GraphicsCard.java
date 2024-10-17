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

class Graphics implements Serializable{
    private String graphicsname;
    private int gsize;
    private double gprice;

    public Graphics(String graphicsname, int gsize, double gprice)
    {
        this.graphicsname=graphicsname;
        this.gsize=gsize;
        this.gprice=gprice;
    }

    Graphics(){

    }
    public String getgraphics()
    {
        return graphicsname;
    }
    public int getgsize()
    {
        return gsize;
    }
    public double gprice()
    {
        return gprice;
    }
    public void setgraphics(String name)
    {
        this.graphicsname=name;
    }
    public void setgsize(int size)
    {
        this.gsize=gsize;
    }
    public void setgprice(double gprice)
    {
        this.gprice=gprice;
    }
    public String toString()
    {
        return "Graphics Card Name : "+getgraphics()+"\nGraphics Size: "+getgsize()+"GB"+"\nCard Price: "+gprice();
    }
}
public class Project_GraphicsCard {
    public static void main(String[] args) throws Exception{
        int choice;
        File graphics_file=new File("Graphicscard.txt");
        Scanner in=new Scanner(System.in);

        ArrayList<Graphics> graphics_arraylist=new ArrayList<>();

        ListIterator graphics_iterator;
        do{
            System.out.println("Enter choice\n1:Insert Card\n2:Display List\n3:Search Card\n4:Delete A Graphics\n5:Update Information\n");
            System.out.println("Press 0 to exit....");
            choice=in.nextInt();
            switch(choice) {
                case 1: {
                    try {
                        ObjectInputStream inp = new ObjectInputStream(new FileInputStream(graphics_file));
                        graphics_arraylist = (ArrayList<Graphics>) inp.readObject();

                    } catch (Exception e) {

                    }

                    System.out.print("How many Graphics Card Do You Want To Enter:");
                    int number2 = in.nextInt();
                    int count = 1;
                    for (int i = 0; i < number2; i++) {
                        System.out.println("Number " + count);
                        System.out.print("Enter Name With Model Number:");
                        String gname = in.next();
                        in.nextLine();
                        System.out.print("Enter Size:");
                        int gsizes = in.nextInt();
                        System.out.print("Enter Price:");
                        double gprices = in.nextDouble();
                        graphics_arraylist.add(new Graphics(gname, gsizes, gprices));
                        count++;

                    }
                    ObjectOutputStream ooutp = new ObjectOutputStream(new FileOutputStream(graphics_file));
                    ooutp.writeObject(graphics_arraylist);
                    ooutp.close();
                    break;

                }

                case 2:
                {
                    try
                {
                    ObjectInputStream inp=new ObjectInputStream(new FileInputStream(graphics_file));
                    graphics_arraylist=(ArrayList<Graphics>)inp.readObject();

                } catch (Exception e) {

                }
                    graphics_iterator=graphics_arraylist.listIterator();
                    System.out.println("----------------------");
                    int count = 1;
                    while(graphics_iterator.hasNext())
                    {
                        System.out.println("Number : "+count);
                        System.out.println(graphics_iterator.next());
                        System.out.println();
                        count++;

                    }
                    System.out.println("----------------------");
                    break;
                }

                case 3:
                {
                    boolean searchgraphics=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(graphics_file));
                        graphics_arraylist=(ArrayList<Graphics>)inp.readObject();

                    } catch (Exception e) {

                    }
                    System.out.print("Enter Name to Search :");
                    String search=in.next();
                    graphics_iterator=graphics_arraylist.listIterator();
                    System.out.println("----------------------");
                    while(graphics_iterator.hasNext())
                    {
                        Graphics gpu= (Graphics)graphics_iterator.next();
                        if(gpu.getgraphics().equalsIgnoreCase(search))
                        {
                            System.out.println(gpu);
                            searchgraphics=true;
                        }
                    }

                    System.out.println("----------------------");
                    if(!searchgraphics) //if not true...
                    {
                        System.out.println("Not Found");

                    }
                    break;

                }

                case 4:
                {
                    boolean searchgraphics = false;
                    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(graphics_file));
                        graphics_arraylist=(ArrayList<Graphics>)inp.readObject();

                    } catch (Exception e) {

                    }
                    graphics_iterator=graphics_arraylist.listIterator();
                    System.out.print("Enter Name to Delete:");
                    String deleteg=in.next();
                    while(graphics_iterator.hasNext())
                    {
                        Graphics g1 = (Graphics) graphics_iterator.next(); //typecasted.see harddisk class...
                        if(g1.getgraphics().equalsIgnoreCase(deleteg))
                        {
                            graphics_iterator.remove();
                            searchgraphics=true;
                        }
                    }
                    if(searchgraphics)
                    {
                       ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(graphics_file));
                        ooutp.writeObject(graphics_arraylist);
                        ooutp.close();
                        System.out.println("Record Deleted..");
                        System.out.println();
                    }else{
                        System.out.println("Record Not Found");
                        System.out.println();
                    }
                    break;
                }
                case 5:
                {   boolean searchgraphics=false;
                    try {
                       ObjectInputStream inp=new ObjectInputStream(new FileInputStream(graphics_file));
                        graphics_arraylist=(ArrayList<Graphics>)inp.readObject();

                    } catch (Exception e) {

                    }
                    graphics_iterator=graphics_arraylist.listIterator();
                    System.out.print("Enter Name to Update:");
                    String updategraphics=in.next();
                    while(graphics_iterator.hasNext())
                    {
                        Graphics g4=(Graphics)graphics_iterator.next();
                        if(g4.getgraphics().equalsIgnoreCase(updategraphics))
                        {
                            System.out.print("Enter New Name and Model:");
                            String newgname=in.next();
                            in.nextLine();
                            System.out.print("Enter New Size:");
                            int newgsize=in.nextInt();
                            in.nextLine();
                            System.out.print("Enter New Price:");
                            double newgprice=in.nextDouble();
                            in.nextLine();
                            Graphics temp = new Graphics();
                            temp = g4;
                            temp.setgraphics(newgname);
                            temp.setgsize(newgsize);
                            temp.setgprice(newgprice);

                            graphics_iterator.set(temp);

                            searchgraphics=true;
                        }
                    }
                    if(searchgraphics)
                    {
                       ObjectOutputStream ooutp=new ObjectOutputStream(new FileOutputStream(graphics_file));
                        ooutp.writeObject(graphics_arraylist);
                        ooutp.close();
                        System.out.println("Record Updated..");
                    }else{
                        System.out.println("Record Not found");
                        System.out.println();
                    }
                    break;
                }
            }
        }while(choice!=0);
    }

}


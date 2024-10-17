package ShopManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


class Keyboard implements Serializable{
    private String keyboard_name;
    private double keyboard_price;

    public Keyboard(String namekeyboard,double pricekeyboard)

    {
        setKeyboard_name(namekeyboard);
        setKeyboard_price(pricekeyboard);
    }

    public void setKeyboard_name(String keyboard_name) {
        this.keyboard_name = keyboard_name;
    }
    public void setKeyboard_price(double keyboard_price) {
        this.keyboard_price = keyboard_price;
    }
    public String getKeyboard_name() {
        return keyboard_name;
    }
    public double getKeyboard_price() {
        return keyboard_price;
    }
    @Override
    public String toString() {
        return "Name: "+ getKeyboard_name()+"\nPrice: "+ getKeyboard_price()+"tk";
    }
}

public class Project_Keyboard {
    public static void main(String[] args) throws Exception {
        ArrayList<Keyboard> arraylist_keyboard =new ArrayList<>();
        File keyboard_file = new File("KeyBoard.txt");
        Scanner in=new Scanner(System.in);
        ListIterator keyboard_list_iterator;
        int choice;
        do
        {
            System.out.println("Enter choice\n1:Insert A Keyboard\n2:Display Keyboard List\n3:Search A Keyboard\n4:Delete A Keyboard\n5:Update Information Of Keyboard\n0:Exit");
            choice=in.nextInt();
            switch(choice)
            {
                case 1:
                {
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(keyboard_file));
                        arraylist_keyboard=(ArrayList<Keyboard>)inp.readObject();

                    } catch (Exception e) {

                    }
                    System.out.print("How Many Keyboard Do You Want To Enter : ");
                    int keyboard_quantity=in.nextInt();
                    int count = 1;
                    for(int i=0;i<keyboard_quantity;i++)
                    {
                        System.out.println("Number : "+count);
                        System.out.print("Enter Name: ");
                        String namekeyboard=in.next();
                        in.nextLine();
                        System.out.print("Enter Price: ");
                        double pricekeyboard=in.nextDouble();
                        arraylist_keyboard.add(new Keyboard(namekeyboard, pricekeyboard));
                        count++;
                    }
                    ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream(keyboard_file));
                    outp.writeObject(arraylist_keyboard);
                    outp.close();
                    System.out.println("Data inserted...");
                    break;
                }
                case 2:
                {
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(keyboard_file));
                        arraylist_keyboard=(ArrayList<Keyboard>)inp.readObject();

                    } catch (Exception e) {

                    }
                    keyboard_list_iterator=arraylist_keyboard.listIterator();
                    System.out.println("-----------------------------------");
                    int count = 1;
                    while(keyboard_list_iterator.hasNext()){
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Number : "+count);
                        System.out.println(keyboard_list_iterator.next());
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        count++;
                    }
                    System.out.println("-----------------------------------");
                    break;
                }
                case 3:
                {  boolean searchkeyboard=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(keyboard_file));
                        arraylist_keyboard=(ArrayList<Keyboard>)inp.readObject();

                    } catch (Exception e) {

                    }
                    keyboard_list_iterator=arraylist_keyboard.listIterator();
                    System.out.print("Enter Name To Search:");
                    String scrkeyboard=in.next();
                    while(keyboard_list_iterator.hasNext()){

                        Keyboard keyboardobject=(Keyboard) keyboard_list_iterator.next();
                        if(keyboardobject.getKeyboard_name().equalsIgnoreCase(scrkeyboard))
                        {   System.out.println("-----------------------");
                            System.out.println(keyboardobject);
                            System.out.println("-----------------------");
                            searchkeyboard=true;
                        }

                    }
                    if(!searchkeyboard)   //if not found
                    {
                        System.out.println("Item not found ");
                        System.out.println();
                    }
                    break;
                }
                case 4:
                {
                    boolean keyboardsearch=false;
                    try {

                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(keyboard_file));
                        arraylist_keyboard=(ArrayList<Keyboard>)inp.readObject();

                    } catch (Exception e) {

                    }
                    keyboard_list_iterator=arraylist_keyboard.listIterator();
                    System.out.print("Enter Name To Delete:");
                    String deletekeyboard=in.next();
                    while(keyboard_list_iterator.hasNext()){

                        Keyboard k2=(Keyboard)keyboard_list_iterator.next();
                        if(k2.getKeyboard_name().equalsIgnoreCase(deletekeyboard))
                        {   System.out.println("-----------------------");
                            System.out.println(k2);
                            System.out.println("-----------------------");
                            keyboard_list_iterator.remove();
                            keyboardsearch=true;
                        }

                    }
                    if(keyboardsearch) //if found
                    {   ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream(keyboard_file));
                        outp.writeObject(arraylist_keyboard);
                        outp.close();
                        System.out.println("Item Deleted ");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not Found.....");
                    }
                    break;
                }
                case 5:
                {
                    boolean keyboardsearch=false;
                    try {
                        ObjectInputStream inp=new ObjectInputStream(new FileInputStream(keyboard_file));
                        arraylist_keyboard=(ArrayList<Keyboard>)inp.readObject();

                    } catch (Exception e) {

                    }
                    keyboard_list_iterator=arraylist_keyboard.listIterator();
                    System.out.print("Enter Name To Update:");
                    String updatekeyboard=in.next();
                    while(keyboard_list_iterator.hasNext()){

                        Keyboard k3=(Keyboard)keyboard_list_iterator.next();
                        if(k3.getKeyboard_name().equalsIgnoreCase(updatekeyboard))
                        {   System.out.println("-----------------------");
                            System.out.println(k3);
                            System.out.println("-----------------------");
                            System.out.print("Enter New Name:");
                            String newnamekeyboard=in.next();
                            in.nextLine();
                            System.out.print("Enter New Price:");
                            double newpricekeyboard=in.nextDouble();
                            k3=new Keyboard(newnamekeyboard, newpricekeyboard);
                            keyboard_list_iterator.set(k3);

                            keyboardsearch=true;
                        }

                    }
                    if(keyboardsearch)  //if true...
                    {
                        ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream(keyboard_file));
                        outp.writeObject(arraylist_keyboard);
                        outp.close();
                        System.out.println("Item Updated ");
                        System.out.println();
                    }
                    else{
                        System.out.println("Not found.....");
                    }
                    break;
                }

            }
        }while(choice!=0);

    }
}





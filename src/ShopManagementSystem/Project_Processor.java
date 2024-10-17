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

class Processor implements Serializable {
    private String processorname;
    private String processorseries;
    private int processorcore;
    private double clockspeed;
    private int genaration;
    private double processorprice;



    public Processor(String processorname, String processorseries, int processorcore, double clockspeed, int genaration,
                     double processorprice) {
        super();
        this.processorname = processorname;
        this.processorseries = processorseries;
        this.processorcore = processorcore;
        this.clockspeed = clockspeed;
        this.genaration = genaration;
        this.processorprice = processorprice;
    }



    public String getProcessorname() {
        return processorname;
    }

    public void setProcessorname(String processorname) {
        this.processorname = processorname;
    }

    public String getProcessorseries() {
        return processorseries;
    }

    public void setProcessorseries(String processorseries) {
        this.processorseries = processorseries;
    }

    public int getProcessorcore() {
        return processorcore;
    }

    public void setProcessorcore(int processorcore) {
        this.processorcore = processorcore;
    }

    public double getClockspeed() {
        return clockspeed;
    }

    public void setClockspeed(double clockspeed) {
        this.clockspeed = clockspeed;
    }

    public int getGenaration() {
        return genaration;
    }

    public void setGenaration(int genaration) {
        this.genaration = genaration;
    }

    public double getProcessorprice() {
        return processorprice;
    }

    public void setProcessorprice(int processorprice) {
        this.processorprice = processorprice;
    }

    @Override
    public String toString() {
        return "Processor Name:" + getProcessorname() + "\nSeries : " + getProcessorseries()  + "\nProcessor Core:"
                + getProcessorcore()  + "\nClock-speed :" + getClockspeed() + "Ghz" + "\nGeneration:"
                + getGenaration() + "th" + "\nPrice:" + getProcessorprice()+"Taka";
    }
}

public class Project_Processor {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        ArrayList<Processor> pro_arraylist = new ArrayList<>();
        File Processor_File = new File("Processor.txt");
        ListIterator pro_list_iterator = null;

        int choice;
        do {
            System.out.println("Enter the choice from below: \n1:Insert Processor\n2:Display Processor List\n3:Search Processor Specifically\n4:Remove A Processor\n5:Update A Processor Information\n0:Exit");
            choice = in.nextInt();
            switch (choice) {
                case 1: {
                    try {
                        ObjectInputStream oinp = new ObjectInputStream(new FileInputStream(Processor_File));
                        pro_arraylist = (ArrayList<Processor>) oinp.readObject(); // mb = motherboard arraylist.
                        // typecasted to Arraylist so that the object reader can collect data as an
                        // object...
                    } catch (Exception e) {

                    }

                    System.out.print("How many processor do you want to enter:");
                    int quantity_loop = in.nextInt();
                      int count = 1;
                    for (int i = 0; i < quantity_loop; i++) {
                        System.out.println("Number "+count);
                        System.out.print("Enter Processor's Name :");
                        String processorname = in.next();
                        in.nextLine();
                        System.out.print("Enter Processor's Series: ");
                        String processorseries = in.next();
                        in.nextLine();
                        System.out.print("Enter Processor's Core:");
                        int processorcore = in.nextInt();
                        System.out.print("Enter Processor's ClockSpeed:");
                        Double clockspeed = in.nextDouble();
                        System.out.print("Enter Processor's Genaration");
                        int genaration = in.nextInt();
                        System.out.println("Enter Processor's price:");
                        int processorprice=in.nextInt();
                        pro_arraylist.add(new Processor(processorname, processorseries, processorcore, clockspeed, genaration, processorprice));
                        count++;

                    }
                    ObjectOutputStream ooutp = new ObjectOutputStream(new FileOutputStream(Processor_File));
                    ooutp.writeObject(pro_arraylist);
                    ooutp.close();
                    break;
                }

                case 2: {
                    try {
                        ObjectInputStream oinp = new ObjectInputStream(new FileInputStream(Processor_File));
                        pro_arraylist = (ArrayList<Processor>) oinp.readObject();
                    } catch (Exception e) {

                    }
                    pro_list_iterator = pro_arraylist.listIterator(); // here the cursor position will traverse through the object and
                    // pick the files...
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
                    int count = 1;
                    while (pro_list_iterator.hasNext()) {
                        System.out.println("Number " + count);
                        System.out.println("_____________________");
                        System.out.println(pro_list_iterator.next());
                        System.out.println("______________________");
                        count++;
                    }
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
                    break;
                }
                case 3: {
                    boolean search_result = false;
                    try {
                        ObjectInputStream oinp = new ObjectInputStream(new FileInputStream(Processor_File));
                        pro_arraylist = (ArrayList<Processor>) oinp.readObject();
                        // typecasted to Arraylist so that the object reader can collect data as an
                        // object...

                    } catch (Exception e) {

                    }
                    pro_list_iterator = pro_arraylist.listIterator();
                    System.out.print("Enter Name To Search: ");
                    String searchboard = in.next();
                    while (pro_list_iterator.hasNext()) {
                        Processor proobj = (Processor) pro_list_iterator.next();
                        if (proobj.getProcessorname().equalsIgnoreCase(searchboard)) {
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println(proobj);
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
                            System.out.println();
                            search_result = true; // the search result is converted to true.
                        }
                    }
                    if (!search_result) // if search_result is not found then...
                    {
                        System.out.println("Product Not Found....");
                        System.out.println();
                    }
                    break;
                }

                case 4: {
                    boolean searchresult = false;
                    try {
                        ObjectInputStream oinp = new ObjectInputStream(new FileInputStream(Processor_File));
                        pro_arraylist = (ArrayList<Processor>) oinp.readObject();
                    } catch (Exception e) {

                    }
                    pro_list_iterator = pro_arraylist.listIterator();
                    System.out.print("Enter Name To Delete:");
                    String deleteboard = in.next();

                    while (pro_list_iterator.hasNext()) {
                        Processor boardobj2 = (Processor) pro_list_iterator.next();
                        if (boardobj2.getProcessorname().equalsIgnoreCase(deleteboard)) {
                            pro_list_iterator.remove(); // removing that object found by the list iterator..
                            searchresult = true;// changing the search result to true..
                        }
                    }
                    if (searchresult) {
                        ObjectOutputStream ooutp = new ObjectOutputStream(new FileOutputStream(Processor_File));
                        ooutp.writeObject(pro_arraylist); // mb = motherboard arraylist
                        ooutp.close();
                        System.out.println("Record Deleted");
                        System.out.println();
                    } else {
                        System.out.println("Item not Found..");
                        System.out.println();
                    }
                    break;

                }
                case 5: {
                    boolean searchresult = false;
                    try {
                        ObjectInputStream oinp = new ObjectInputStream(new FileInputStream(Processor_File));
                        pro_arraylist = (ArrayList<Processor>) oinp.readObject();
                    } catch (Exception e) {

                    }
                    pro_list_iterator = pro_arraylist.listIterator();
                    System.out.print("Enter Name to Update:");
                    String updateprocessor = in.next();
                    while (pro_list_iterator.hasNext()) {
                        Processor proobj2 = (Processor) pro_list_iterator.next();
                        if (proobj2.getProcessorname().equalsIgnoreCase(updateprocessor)) {

                            System.out.print("Enter New Processor's Name :");
                            String processorname = in.next();
                            in.nextLine();
                            System.out.print("Enter New Processor's Series: ");
                            String processorseries = in.next();
                            in.nextLine();
                            System.out.print("Enter New Processor's core:");
                            int processorcore = in.nextInt();
                            System.out.print("Enter New Processor's ClockSpeed:");
                            Double clockspeed = in.nextDouble();
                            System.out.print("Enter New Processor's Genaration:");
                            int genaration = in.nextInt();
                            System.out.println("Enter New Processor's price:");
                            double processorprice=in.nextDouble();

                            Processor p1 = new Processor(processorname,processorseries,processorcore,clockspeed,genaration,processorprice);

                            pro_list_iterator.set(p1);



                            searchresult = true;
                        }
                    }
                    if (searchresult) // if search result is true...

                    {
                        ObjectOutputStream ooutp = new ObjectOutputStream(new FileOutputStream(Processor_File));
                        ooutp.writeObject(Processor_File);
                        ooutp.close();

                    }
                        else{
                        System.out.println("Record Updated");
                        System.out.println();
                    }

                }
            }

        } while (choice != 0);
    }
}

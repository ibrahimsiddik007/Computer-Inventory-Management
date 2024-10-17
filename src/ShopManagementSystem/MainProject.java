package ShopManagementSystem;

//Group Number -14
//         1. MD. Ibrahim Siddik (2211632042)
//        2. Farhan Ishraque (2212002042)
//        3. MD Yeasin Arafat (2) (2212337042)
//        4. Saif Barkatullah (2212088642)



import java.util.Scanner;


public class MainProject {
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner in = new Scanner(System.in);


        System.out.println("Welcome to X Computer Shop.");
        System.out.println("Please enter your login and password....");


        System.out.print("Enter User Name:");
        String username = in.next();
        System.out.print("Enter Password:");
        int password = in.nextInt();
        if (username.equals("admin") && password == 1234) {

            do {
                System.out.println("Hello,Admin!!!");
                System.out.println("Choose A Sub-Category: ");
                System.out.println("___________________________________________________________________________________________________________________________");
                System.out.println("1:Processor\n2:Ram\n3:Hard-Disk(HDD/SSD)\n4:Graphics-card(GPU)\n5:Motherboard\n6:Power Supply\n7:Keyboard\n8:Mouse\n9:Monitor.");
                System.out.println("Press 0 to exit the program...");
                choice = in.nextInt();
                switch (choice) {
                    case 1: {
                        Project_Processor.main(null);
                        break;
                    }
                    case 2: {
                        Project_Ram.main(null);
                        break;
                    }
                    case 3: {

                        Project_Harddisk.main(null);
                        break;
                    }
                    case 4: {

                        Project_GraphicsCard.main(null);
                        break;
                    }
                    case 5: {

                        Project_Motherboard.main(null);
                        break;
                    }
                    case 6: {
                        Project_Powersupply.main(null);
                        break;
                    }
                    case 7: {

                        Project_Keyboard.main(null);
                        break;
                    }
                    case 8: {
                       Project_Mouse.main(null);
                        break;
                    }
                    case 9: {
                       Project_Monitor.main(null);
                        break;
                    }
                    default:
                        System.out.println("Invalid Input");
                }
            } while (choice != 0);

        }
        System.out.println("\n.Exiting the program...");
    }
}



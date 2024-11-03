
package ivon11;

import java.util.Scanner;
import java.util.regex.Pattern;


public class released {
   
     Scanner sc = new Scanner(System.in);

    public void rtransaction() {
        String response;
        do {
            System.out.println("\n---------------------------");
            System.out.println("Releasing and received 4ps:");
            System.out.println("1. Add released:");
            System.out.println("2. View released:");
            System.out.println("3. Update released:");
            System.out.println("4. Delete released:");
            System.out.println("5. Exit:");

            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            released rd = new released(); 
            switch (act) {
                case 1:
                    rd.addreleased();
                   
                    break; 
                case 2:
                    rd.viewreleased();                         
                    break;
                case 3: rd.viewreleased(); 
                     rd.updatereleased(); 
                     rd.viewreleased(); 
                    break;
                case 4:
                    rd.viewreleased();
                    rd.deletereleased();
                    rd.viewreleased(); 
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
            
            System.out.println("Do you want to continue? (yes/no):");
            response = sc.next();
            
        } while (response.equalsIgnoreCase("yes"));     
    }
    
   public void addreleased() {
    String rdate = null;
    double ramount = 0.0;
    String rstat = null;
    boolean validInput = false;

    
    while (!validInput) {
        System.out.print("Enter Released Date (YYYY-MM-DD): ");
        rdate = sc.next();

        if (isValidDate(rdate)) {
            validInput = true;
        } else {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    
    validInput = false;
    while (!validInput) {
        System.out.print("Enter Released Amount: ");
        if (sc.hasNextDouble()) {
            ramount = sc.nextDouble();
            if (ramount > 0) {
                validInput = true;
            } else {
                System.out.println("Amount must be a positive number.");
            }
        } else {
            System.out.println("Invalid input. Please enter a numeric value.");
            sc.next(); 
        }
    }

    validInput = false;
    while (!validInput) {
        System.out.print("Enter Released Status: ");
        rstat = sc.next();
        
        if (!rstat.trim().isEmpty()) {
            validInput = true;
        } else {
            System.out.println("Status cannot be empty.");
        }
    }

    String qry = "INSERT INTO tbl_released (r_date, r_amount, r_status) VALUES (?, ?, ?)";
    config conf = new config();
    conf.addRecord(qry, rdate, ramount, rstat);
}


private boolean isValidDate(String date) {
    String regex = "^\\d{4}-\\d{2}-\\d{2}$";
    return Pattern.matches(regex, date);
}

    public void viewreleased() {
        String qry = "SELECT * FROM tbl_released";
        String[] hrds = {"r_id", "r_date", "r_amount", "r_status"};
        String[] clms = {"r_id", "r_date", "r_amount", "r_status"};
        config conf = new config();
        conf.viewRecords(qry, hrds, clms);
    }
    
    private void updatereleased() {
        config conf = new config();

        System.out.print("Enter ID to update: ");
        int rid = sc.nextInt();
       

      
        while (conf.getSingleValue("SELECT r_id FROM tbl_released WHERE r_id = ?", rid) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select released ID Again:");
            rid = sc.nextInt();
           
}
    
    
     System.out.print("Enter New released date: ");
        String rdate = sc.next();
        System.out.print("Enter New Released amount: ");
        double ramount = sc.nextDouble();
        System.out.print("Enter New Released Status: ");
        String rstat = sc.next();
        
        while (conf.getSingleValue("SELECT r_id FROM tbl_released WHERE r_id = ?", rid) == 0) {
            System.out.println("Selected ID  to update doesn't exist!");
            System.out.println("Select released ID Again:");
            rid = sc.nextInt();
        String qry = "UPDATE tbl_released SET r_id = ?, r_date = ?, r_status = ? WHERE r_id = ?";
        conf.updateRecord(qry, rdate, ramount, rstat, rid);
        
         
          }
    }
           
    
    
    
     public void deletereleased(){
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter Id to Delete:");
        int rid = sc.nextInt();
         
        
          while (conf.getSingleValue("SELECT r_id FROM tbl_released WHERE r_id = ?", rid) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select releaseed ID Again:");
            rid = sc.nextInt();
           
          }
        
        String qry ="DELETE FROM tbl_released WHERE r_id =?";
   
     }
}
    



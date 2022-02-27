/*Jemima Vijayasenan
May 24, 2021
Purpose: Create fully functionally program for a dentist office*/

import java.util.*;


class Main {
  public static void main(String[] args) {
    //set up
    Scanner input = new Scanner (System.in);

    // initialize Variables/arrays
    String pass, main = "", again ="", viewAgain ="", bookAgain ="";
    int mode, apptChoice, day,invenChoice, itemNum, patChoice, counter=0, employChoice;
    int quant [] = {12, 7, 10, 6, 8, 10, 8};
    String signInOut [][] = new String [6][2]; //6 employees, 2 time options (sign in, sign out)
    String patInfo [][] = new String [50][4]; //50 patients max, 4 peices of information each - name, age, allergies, last dentist visit (used 50 since we dont know how to use lists (arrays with unknown size))
    String employeeName [] = {"Lisa     ", "James    ", "William  ", "Jennifer ", "Dr. Broda", "Dr. Anwar"};
    String appts [][][] = { // [5][5][2] --> 5 days, 5 time slots each day, 2 doctors per time slot
                         
                          //MONDAY -- 1pm, 2pm, 3pm, 5pm, 6pm -- Dr. Broda, Dr. Anwar
                          {{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"}},

                          //TUESDAY
                          {{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"}},

                          //WEDNESDAY
                          {{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"}},

                          //THURS
                          {{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"}},

                          //FRIDAY  
                          {{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"},{"Open","Open"}}

                          };

    //populate patient information array to avoid "nullPointerException" error when returning value from method
    for(int row=0; row<patInfo.length; row++){
      for(int col=0; col<patInfo[row].length; col++){
        patInfo [row][col] = "";
      }//end col
    }//end row

    //populate Employee sign-in/out array to avoid "nullPointerException" error when returning value from method
    for(int row=0; row<signInOut.length; row++){
      for(int col=0; col<signInOut[row].length; col++){
        signInOut [row][col] = "-";
      }//end col
    }//end row

    //Introduction
    System.out.println("\n---------------------------------------------------\n------ SUNSHINE DENTAL CLINIC - ADMIN CENTER ------\n---------------------------------------------------");

    do{

      //Print Main Menu
      System.out.println("\n--- Welcome! ---");
      System.out.println("\n1. Client Appointment Services\n2. Employee Sign-in/Sign-out\n3. Patient Record Services\n4. Equipment/Inventory Services\n5. Quit");
      System.out.print("Select Option (#): ");
      mode = input.nextInt();

      //APPOINTMENT SERVICES
      if (mode ==1){
        do{
          System.out.println("\n-- Apointment Services --\n");
          System.out.print("\n1. Book an Appointnment\n2. View Appointment Availability\n3. Back to Main Menu\n\n**Any Appointments booked will override existing appointments in that time slot, please view appointment availability to aviod double booking**\n**Appointments are only available for a week at a time**\n\nSelect Option (#): ");
          apptChoice = input.nextInt();

          //book an appt
          if (apptChoice ==1){

            do{
              appts = appointments(appts);
              System.out.print("\nWould you like to book another appointment? (y/n): ");
              bookAgain = input.next();
            }while (bookAgain.equalsIgnoreCase("y"));
            
            System.out.print("Would you like to return to appointment services menu or quit? ('y' for menu/any other key to quit): ");
            again = input.next();

          }

          //view appts
          else if (apptChoice ==2){
            do{
              System.out.print("\n1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday");
              System.out.print("\n\nWhich day would you like to view apointments for (#): ");
              day = input.nextInt();
              day --;

              appointments(day,appts);

              System.out.print("\nWould you like to view appointment availability for another time? (y/n): ");
              viewAgain = input.next();
            
            }while (viewAgain.equalsIgnoreCase("y"));

            System.out.print("\nWould you like to return to appointment services menu or quit? ('y' for menu/any other key to quit): ");
            again = input.next();


          }
          //Return to main
          else if (apptChoice ==3){
            main = "y";
            break;
          }

          //invalid input
          else {
            System.out.println("\n*** ERROR INVALID CHOICE ***\n....Redirecting");
            again = "y"; 
          }

        }while (again.equalsIgnoreCase("y"));
      }//End of Appointments


      //EMPLOYEE SCHEDULE SERVICES
      else if (mode ==2){
        do{
          //Ask user information
          System.out.println("\n--EMPLOYEE TIME LOG--\n\n1. Employee Sign-in\n2. Employee Sign-out\n3. View Time Log\n4. Return to Main");
          System.out.print("\nSelect Option (#): ");
          employChoice = input.nextInt();

          //Sign in method
          if (employChoice ==1){
            signInOut = signIn(signInOut);
          }

          //sign-out method
          else if (employChoice ==2){
            signInOut = signOut(signInOut);
          }

          //view full time log array in table 
          else if (employChoice==3){
            System.out.println("\nName:\t\t\tSign-In Time:\t\t\tSign-Out Time:");
            for (int row=0; row<signInOut.length; row++){
              System.out.print(employeeName[row]+"\t\t\t");
              for(int col=0; col<signInOut[row].length;col++){
                System.out.print(signInOut[row][col]+"\t\t\t\t\t\t");
              }//end col
              System.out.println();
            }//end row
          }

          //Back to main menu
          else if (employChoice ==4){
            main = "y";
            break;
          }

          //invalid input
          else{
            System.out.println("\n*** ERROR INVALID CHOICE ***");
          }

          //Ask user if they want to continue in patient records
          System.out.print("\nWould you like to return to Employee time log menu or quit? ('y' for menu/any other key to quit): ");
          again = input.next();

        }while (again.equalsIgnoreCase("y"));
        
      }//End of Employee Schedule

      //PATIENT RECORDS
      else if (mode ==3){
        do{
        
          //Ask user information
          System.out.println("\n--- PATIENT RECORD SERVICES ---");
          System.out.print("\n1. Create New Record\n2. View Existing Record\n3. Return to Main Menu\nSelect Option (#): ");
          patChoice = input.nextInt();

          //Create a new patient record
          if (patChoice ==1){
            patInfo = patRecord(counter, patInfo);
            counter++;
          }

          //view an existing record
          else if (patChoice ==2){
            patRecord(patInfo);
          }

          //return to main menu
          else if (patChoice ==3){
            main = "y";
            break;
          }

          //invalid input
          else{
            System.out.println("\n*** ERROR INVALID CHOICE ***");
          }

          //Ask user if they want to continue in patient records
          System.out.print("\nWould you like to return to Patient Record Services menu or quit? ('y' for menu/any other key to quit): ");
          again = input.next();

        }while (again.equalsIgnoreCase("y"));
        
      }//End of patient Records

      //EQUIPMENT INVENTORY
      else if (mode ==4){

        do{
          //Ask user information
          System.out.println("\n--- EQUIPMENT INVENTORY SERVICES ---");
          System.out.print("\n1. View Current Inventory\n2. Update Current Inventory\n3. Return to Main Menu\nSelect Option (#): ");
          invenChoice = input.nextInt();
      
          //View current inventory
          if (invenChoice == 1){
            equipment(quant);

          }

          //Update inventory
          else if (invenChoice ==2){
            System.out.println("\n1. Mouth Mirror\n2. Sickle Probe\n3. Scaler\n4. Suction Device\n5. Dental Drill\n6. Dental Syringe\n7. Molds");
            System.out.print("\nWhich product whould you like to update the inventory for? (#): ");
            itemNum = input.nextInt();
            itemNum--;

            quant = equipment(itemNum, quant);
          }

          //return to main menu
          else if (invenChoice ==3){
            main = "y";
            break;
          }

          //invalid input
          else{
            System.out.println("\n*** ERROR INVALID CHOICE ***");
          }

        //Ask user if they want to continue in inventory
        System.out.print("\nWould you like to return to Inventory services menu or quit? ('y' for menu/any other key to quit): ");
        again = input.next();

        }while (again.equalsIgnoreCase("y"));
      
      }//End of Inventory

      //QUIT
      else if (mode ==5){
        break;
      }//Quit main menu

      //ERROR CHECK
      else{
        System.out.println("\n*** ERROR INVALID CHOICE ***\n....Redirecting to Main Menu");
        main = "y";   
      }

    } while (main.equalsIgnoreCase("y"));

  }//End Main Method


  public static String [][][] appointments(String appts[][][]){
    //Initialize variables/arrays
    int day, time, name;
    Scanner input = new Scanner (System.in);

      //Ask user for input
      System.out.print("\n1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday");
      System.out.print("\nChoose a day for the apointment (#): ");
      day = input.nextInt();
      System.out.print("\n1. 1pm\n2. 2pm\n3. 3pm\n4. 4pm\n5. 5pm");
      System.out.print("\nChoose a time for the apointment (#): ");
      time = input.nextInt();
      System.out.print("\n1. Dr. Broda\n2. Dr. Anwar");
      System.out.print("\nChoose the Dentist you would like to see (#): ");
      name = input.nextInt();

      //Book appointments/overide booked appointments
      if (appts[day-1][time-1][name-1].equalsIgnoreCase("Open")){
        appts[day-1][time-1][name-1] = "Booked";
        System.out.print("\n*Appointment Booked*");
        
      }
      else if (!appts[day-1][time-1][name-1].equalsIgnoreCase("Open")){
        System.out.print("*Previous Appointment Overiden*\n*Appointment Booked*");
      }

    return appts;
  }//end of book appointments method

  public static void appointments( int d,String appts[][][]){
    //Initialize variables/arrays
    String time [] = {"1 pm:", "2 pm:", "3 pm:", "5 pm:", "6 pm:"};

    //Print out arrat in table
    System.out.println("\nTime\t\tDr. Broda:\t\tDr. Anwar:");
    for(int x=0; x<appts[d].length; x++){
      System.out.print(time[x] + "\t\t");
      for(int y=0; y<appts[d][x].length; y++){
        System.out.print(appts[d][x][y]+"\t\t\t");
      }
      System.out.println();
    }//end of schedule for loop

  }//end of view appoinments

  public static void equipment (int quant []){
    //Initialize variables/arrays
    String equip [] = {"Mouth Mirror", "Sickle Probe", "Scaler", "Suction Device", "Dental Drill", "Dental Syringe", "Molds"};

    System.out.println();
    //print the inventory
    for (int x=0; x<equip.length; x++){
      System.out.println(equip[x] + ": " + quant[x]);
    }

  }//end of view equipment method

  public static int [] equipment (int itemNum, int quant []){
    Scanner input = new Scanner (System.in);

    //update inventory for chosen item
    System.out.print("Please enter the new quantity for the item: ");
    quant[itemNum] = input.nextInt();
    
    //return value
    return quant;
  }

  public static String [][] patRecord (int counter, String patInfo [][]){
    //Initialize variables/arrays
    Scanner input = new Scanner (System.in);
    String info [] = {"'s Full Name (no spaces): ", "'s Age: ", "'s Allergies (list and sparate only using commas(,)): ", "'s Last Dentist Visit (Prefered format: DD/MM/YYYY): "};

    System.out.println("\n--- CREATING NEW RECORD ---");
    //Ask user to input information
    for(int x=0; x<patInfo[counter].length; x++){
      System.out.print("\nPatient" + info[x]);
      patInfo[counter][x] = input.next();
    }

    System.out.println("\n**Record Successfully Created**");
    
    //return value
    return patInfo;
  }//end of create a new patient record method

  public static void patRecord (String patInfo [][]){
    Scanner input = new Scanner (System.in);
    String name;
    String info [] = {"Full Name: ", "Age: ", "Allergies: ", "Last Dentist Visit: "};

    //Ask user for informtaion
    System.out.print("\nEnter the Full name of the Patient you would like to view the record for (no spaces): ");
    name = input.next();

    //Look for name in records
    for(int x=0; x<patInfo.length; x++){
      if (patInfo[x][0].equalsIgnoreCase(name)){
        System.out.println("\n*Record Found!*\n\n--PATIENT RECORD--");
        //print out the record
        for(int y=0; y<patInfo[x].length; y++){
          System.out.println( info[y] + " " + patInfo[x][y]);
        }//end printing record
        break;
      }

      else if (!patInfo[x][0].equalsIgnoreCase(name) && (x == (patInfo.length-1))){
        System.out.println("\n*No Record Under This Name*");
        break;
      }

      // else{
      //   continue;
      // }

    }//end of searching/printing for patitent record
  }//end of view existing patient records method

  public static String [][] signIn(String in[][]){
    Scanner input = new Scanner (System.in);

    //Initialize variables/arrays
    int employee;
    String time;

    //print out employees/ get user input
    System.out.println("\n1. Lisa\n2. James\n3. William\n4. Jennifer\n5. Dr. Broda\n6. Dr. Anwar");
    System.out.print("Select Employee (#): ");
    employee = input.nextInt();

    System.out.print("\nEnter the Sign-in Time (##:##): ");
    time = input.next();

    //format check
    while (!time.contains(":")){
      System.out.print("\n**INVALID**");
      System.out.print("\nEnter the Sign-in Time (##:##): ");
      time = input.next();
    }

    //Store value
    in[employee-1][0] = time;

    System.out.println("*Sign-in Successful*");

    //return value
    return in;
  }// end of sign in method

  public static String [][] signOut(String out[][]){
    Scanner input = new Scanner (System.in);

    //Initialize variables/arrays
    int employee;
    String time;

    //print out employees/ get user input
    System.out.println("\n1. Lisa\n2. James\n3. William\n4. Jennifer\n5. Dr. Broda\n6. Dr. Anwar");
    System.out.print("Select Employee (#): ");
    employee = input.nextInt();

    System.out.print("\nEnter the Sign-out Time (##:##): ");
    time = input.next();

    //format check
    while (!time.contains(":")){
      System.out.print("\n**INVALID**");
      System.out.print("\nEnter the Sign-Out Time (##:##): ");
      time = input.next();
    }

    //store value
    out[employee-1][1] = time;

    //return value
    System.out.println("*Sign-out Successful*");

    return out;
  }// end of sign in method


}//End Class
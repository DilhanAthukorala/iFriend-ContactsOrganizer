import java.util.*;
import java.time.*;
public class iFriend {
    private final static Scanner scanner = new Scanner(System.in);

    //CREATING ARRAYS
    public static String[] idArray=new String[0];
    public static String[] nameArray=new String[0];
    public static String[] phoneNumberArray=new String[0];
    public static String[] companyNameArray=new String[0];
    public static double[] salaryArray=new double[0];
    public static String[] birthDayArray=new String[0];

    //--------------------------------------------HOME PAGE--------------------------------------------
    public static void homePage(){
        clearConsole();
   
        System.out.println("===========================================================");
        System.out.println("|                         iFRIEND                         |");
        System.out.println("|                   CONTACTS ORGANAIZER                   |");
        System.out.println("===========================================================");
        System.out.println();
        System.out.println("     [01] ADD Contacts ");
        System.out.println("     [02] UPDATE Contacts ");
        System.out.println("     [03] DELETE Contacts ");
        System.out.println("     [04] SEARCH Contacts ");
        System.out.println("     [05] LIST Contacts ");
        System.out.println("     [06] EXIT ");
        System.out.println();
        System.out.print("Enter an option to continue -> ");
        int option=scanner.nextInt();

        switch (option) {
            case 1:addContacts();
                break;
            case 2:updateContacts();
                break;
            case 3:deleteContacts();
                break;
            case 4:searchContacts();
                break;
            case 5:listContacts();
                break;
            case 6:
                System.out.println("Exit Program...");
                System.exit(0);
                 break;
            default:
                System.out.println("Wrong Input...Try again");
                slowProgram();
                homePage();
                break;
        }
    }


    //------------------------------------------ADD CONTACTS-------------------------------------------
    public static void addContacts(){
        clearConsole();
        extendArrays();

        int length=idArray.length;

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|              ADD contact to list                    |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        //generating the ID
        String id=String.format("C%04d",length);
        System.out.println(id);
        System.out.println("=======");
        System.out.println();

        //name input
        System.out.print("Name              : ");
        String name=scanner.next();

        //phone number input 
        String phoneNumber;
        L1:do{ 
            System.out.print("Phone Number      : ");
            phoneNumber=scanner.next();

            //validating phone number
            //boolean bool=isValidPhoneNumber(phoneNumber);
            if(!isValidPhoneNumber(phoneNumber)){
                System.out.println("\n\tInvalid phone number..\n");
                System.out.print("Do you want to add phone number again (Y/N) : ");
                char option=scanner.next().charAt(0);
                if(Character.toLowerCase(option)=='y'){
                    continue L1;
                }else{
                    homePage();
                }
            }else{
                break;
            }
        }while(true);

        //input company name
        System.out.print("Company Number    : ");
        String companyName=scanner.next();

        //input salary
        double salary;
        L2:do{
            System.out.print("Salary            : ");
            salary=scanner.nextDouble();

            //validating salary
            if(salary<0){
                System.out.println("\n\tInvalid salary..\n");
                System.out.print("Do you want to add salary again (Y/N) : ");
                char option=scanner.next().charAt(0);
                if(Character.toLowerCase(option)=='y'){
                    continue L2;
                }else{
                    homePage();
                }
            }else{
                break;
            }
        }while (true);

        //input birth day
        String birthDay;
        L3:do{
            System.out.print("B'Day(YYYY-MM-DD) : ");
            birthDay=scanner.next();

            //validating birthday
            if (!birthDayValidation(birthDay)) {
                System.out.println("\n\tInvalid birth Day..\n");
                System.out.print("Do you want to add birth day again (Y/N) : ");
                char option=scanner.next().charAt(0);
                if(Character.toLowerCase(option)=='y'){
                    continue L3;
                }else{
                    homePage();
                }
            }else{
                break;
            }
        }while (true);

        System.out.println("Contact has been added successfully..");

        idArray[length-1]=id;
        nameArray[length-1]=name;
        phoneNumberArray[length-1]=phoneNumber;
        companyNameArray[length-1]=companyName;
        salaryArray[length-1]=salary;
        birthDayArray[length-1]=birthDay;

        System.out.print("Do you want to add another contact again (Y/N) : ");
        char option=scanner.next().charAt(0);
        if(Character.toLowerCase(option)=='y'){
            addContacts();
        }else{
            homePage();
        }

    }

    //-----------------------------------------UPDATE CONTACTS-----------------------------------------
    public static void updateContacts(){
        clearConsole();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|                    UPDATE Contact                   |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        System.out.print("Search contact by name or phone number - ");
        String str = scanner.next();

        int count=search(str);

        if(count==-1){
            System.out.println("Wrong name or phone number...Try again");
            updateContacts();
        }else{  
            System.out.println("\n\tContact ID        : "+idArray[count]);
            System.out.println("\tName              : "+nameArray[count]);
            System.out.println("\tPhone Number      : "+phoneNumberArray[count]);
            System.out.println("\tCompany Name      : "+companyNameArray[count]);
            System.out.println("\tSalary            : "+salaryArray[count]);
            System.out.println("\tB'Day(YYYY-MM-DD) : "+birthDayArray[count]);

            System.out.println("\nWhat do you want to update...\n");
            System.out.println("\t[01] Name");
            System.out.println("\t[02] Phone Number");
            System.out.println("\t[03] Company Name");
            System.out.println("\t[04] Salary");

            System.out.print("\nEnter an option to continue -> ");
            int option=scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("\033[8A");// Move the cursor up five lines
                    System.out.print("\033[0J");// Clear the lines

                    System.out.println(" Update Name");
                    System.out.println("=============");
                    System.out.print("\nInput new name : ");
                    String newName=scanner.next();

                    System.out.println("\nContact has been updated sucessfully...\n ");
                    nameArray[count]=newName;

                    System.out.print("Do you want to update another contact again (Y/N) : ");
                    char option1=scanner.next().charAt(0);
                    if(Character.toLowerCase(option1)=='y'){
                        updateContacts();
                    }else{
                        homePage();
                    }
                    break;

                case 2:
                    System.out.print("\033[8A");// Move the cursor up five lines
                    System.out.print("\033[0J");// Clear the lines

                    System.out.println(" Update Phone Number");
                    System.out.println("=====================");
                    String newPhoneNumber;
                    L:do{
                        System.out.print("\nInput new phone Numbr : ");
                        newPhoneNumber=scanner.next();

                        if(!isValidPhoneNumber(newPhoneNumber)){
                            System.out.println("\n\tInvalid phone number..\n");
                            System.out.print("Do you want to add phone number again (Y/N) : ");
                            char opt=scanner.next().charAt(0);
                            if(Character.toLowerCase(opt)=='y'){
                                continue L;
                            }else{
                                homePage();
                            }
                        }else{
                            break;
                        }
                    }while (true);

                    System.out.println("\nContact has been updated sucessfully...\n ");
                    phoneNumberArray[count]=newPhoneNumber;

                    System.out.print("Do you want to update another contact again (Y/N) : ");
                    char option2=scanner.next().charAt(0);
                    if(Character.toLowerCase(option2)=='y'){
                        updateContacts();
                    }else{
                        homePage();
                    }
                    break;
            
                case 3:
                    System.out.print("\033[8A");// Move the cursor up five lines
                    System.out.print("\033[0J");// Clear the lines

                    System.out.println(" Update Company Name");
                    System.out.println("=====================");
                    System.out.print("\nInput new company name : ");
                    String newCompanyName=scanner.next();

                    System.out.println("\nContact has been updated sucessfully...\n ");
                    companyNameArray[count]=newCompanyName;

                    System.out.print("Do you want to update another contact again (Y/N) : ");
                    char option3=scanner.next().charAt(0);
                    if(Character.toLowerCase(option3)=='y'){
                        updateContacts();
                    }else{
                        homePage();
                    }
                    break;

                case 4:
                    System.out.print("\033[8A");// Move the cursor up five lines
                    System.out.print("\033[0J");// Clear the lines

                    System.out.println(" Update Salary");
                    System.out.println("===============");
                    double newSalary;
                    L:do{
                        System.out.print("\nInput new Salary : ");
                        newSalary=scanner.nextDouble();

                        if(newSalary<0){
                            System.out.println("\n\tInvalid Salary..\n");
                            System.out.print("Do you want to add salary again (Y/N) : ");
                            char opt=scanner.next().charAt(0);
                            if(Character.toLowerCase(opt)=='y'){
                                continue L;
                            }else{
                                homePage();
                            }
                        }else{
                            break;
                        }
                    }while (true);

                    System.out.println("\nContact has been updated sucessfully... /n");
                    salaryArray[count]=newSalary;

                    System.out.print("Do you want to update another contact again (Y/N) : ");
                    char option4=scanner.next().charAt(0);
                    if(Character.toLowerCase(option4)=='y'){
                        updateContacts();
                    }else{
                        homePage();
                    }
                    break;
                default:
                    System.out.println("Wrong Input...Try again");
                    slowProgram();
                    updateContacts();
                    break;
            }

        }
    }

    //-----------------------------------------DELETE CONTACTS-----------------------------------------
    public static void deleteContacts(){
        clearConsole();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|                    DELETE Contact                   |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        System.out.print("Search contact by name or phone number - ");
        String str = scanner.next();

        int count=search(str);

        if(count==-1){
            System.out.println("Wrong name or phone number...Try again");
            updateContacts();
        }else{  
            System.out.println("\n\tContact ID        : "+idArray[count]);
            System.out.println("\tName              : "+nameArray[count]);
            System.out.println("\tPhone Number      : "+phoneNumberArray[count]);
            System.out.println("\tCompany Name      : "+companyNameArray[count]);
            System.out.println("\tSalary            : "+salaryArray[count]);
            System.out.println("\tB'Day(YYYY-MM-DD) : "+birthDayArray[count]);

            System.out.println("\nDo you want to delete this contact (Y/N) : ");
            char option4=scanner.next().charAt(0);
                if(Character.toLowerCase(option4)=='y'){
                    int length=idArray.length;

                    String[] temporaryIdArray=new String[length-1];
                    String[] temporaryNameArray=new String[length-1];
                    String[] temporaryPhoneNumberArray=new String[length-1];
                    String[] temporaryCompanyNameArray=new String[length-1];
                    double[] temporarySalaryArray=new double[length-1];
                    String[] temporaryBirthDayArray=new String[length-1];

                    for (int i = 0; i < count; i++) {
                        temporaryIdArray[i] = idArray[i];
                        temporaryNameArray[i] = nameArray[i];
                        temporaryPhoneNumberArray[i] = phoneNumberArray[i];
                        temporaryCompanyNameArray[i] = companyNameArray[i];
                        temporarySalaryArray[i] = salaryArray[i];
                        temporaryBirthDayArray[i] = birthDayArray[i];
                    }

                    for (int i = count; i < length-1; i++) {
                        temporaryIdArray[i]=idArray[i+1];
                        temporaryNameArray[i]=nameArray[i+1];
                        temporaryPhoneNumberArray[i]=phoneNumberArray[i+1];
                        temporaryCompanyNameArray[i]=companyNameArray[i+1];
                        temporarySalaryArray[i]=salaryArray[i+1];
                        temporaryBirthDayArray[i]=birthDayArray[i+1];
                    }

                    idArray=temporaryIdArray;
                    nameArray=temporaryNameArray;
                    phoneNumberArray=temporaryPhoneNumberArray;
                    companyNameArray=temporaryCompanyNameArray;
                    salaryArray=temporarySalaryArray;
                    birthDayArray=temporaryBirthDayArray;
                    
                    System.out.println("\n\t\tCustomr has been deleted sucessfully...\n");
                }else{
                    homePage();
                }
        }
        System.out.print("Do you want to delete another contact again (Y/N) : ");
        char option=scanner.next().charAt(0);
        if(Character.toLowerCase(option)=='y'){
            deleteContacts();
        }else{
            homePage();
        }
    }

    //-----------------------------------------SEARCH CONTACTS-----------------------------------------
    public static void searchContacts(){
        clearConsole();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|                    SEARCH Contact                   |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        System.out.print("Search contact by name or phone number - ");
        String str = scanner.next();

        int count=search(str);

        if(count==-1){
            System.out.println("Wrong name or phone number...Try again");
            updateContacts();
        }else{  
            System.out.println("\n\tContact ID        : "+idArray[count]);
            System.out.println("\tName              : "+nameArray[count]);
            System.out.println("\tPhone Number      : "+phoneNumberArray[count]);
            System.out.println("\tCompany Name      : "+companyNameArray[count]);
            System.out.println("\tSalary            : "+salaryArray[count]);
            System.out.println("\tB'Day(YYYY-MM-DD) : "+birthDayArray[count]);

            System.out.print("Do you want to search another contact again (Y/N) : ");
            char option=scanner.next().charAt(0);
            if(Character.toLowerCase(option)=='y'){
                searchContacts();
            }else{
                homePage();
            }
        }
    }

    //-----------------------------------------LIST CONTACTS-------------------------------------------
    public static void listContacts(){
        clearConsole();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("|                    LIST Contact                   |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();
        System.out.println("     [01] Sorting by Name ");
        System.out.println("     [02] Sorting by Salary ");
        System.out.println("     [03] Sorting by Birthday ");
        System.out.println("     [04] Exit");
        System.out.println();
        System.out.print("Enter an option to continue -> ");
        int option=scanner.nextInt();

        switch (option) {
            case 1:sortByName();
                break;
            case 2:sortBySalary();
                break;
            case 3:sortByBirthday();
                break;
            case 4:
                homePage();
                break;
            default:
                System.out.println("Wrong Input...Try again");
                slowProgram();
                listContacts();
                break;
        }
    }

    //Sorting by Name
    public static void sortByName(){
        String[] idSortByName=new String[idArray.length];
        for (int i = 0; i < idSortByName.length; i++) {
            idSortByName[i]=idArray[i];
        }

        for (int i = 0; i < idArray.length-1; i++) {
            for (int j = 0; j < idSortByName.length-1-i; j++) {
                int index1 = search(idSortByName[j]);
				int index2 = search(idSortByName[j+1]);
                if(compareName(nameArray[index1],nameArray[index2])>0){
                    String temp=idSortByName[j];
                    idSortByName[j]=idSortByName[j+1];
                    idSortByName[j+1]=temp;
                }
            }
        }

        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println("\t\t|                    LIST Contact by Name                  |");
        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println();
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-12s | %-10s | %-10s | %-10s |\n","Contact ID","Name","Phone Number","Company","Salary","Birthday");
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");
        
        int j;
        for (int i = 0; i <idSortByName.length; i++) {
            j=search(idSortByName[i]);

            System.out.printf("| %-10s | %-15s | %-12s | %-10s | %10.2f | %-10s |\n",idArray[j],nameArray[j],phoneNumberArray[j],companyNameArray[j],salaryArray[j],birthDayArray[j]);
        }
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");

         System.out.print("Do you want to list a another list (Y/N) : ");
            char option=scanner.next().charAt(0);
            if(Character.toLowerCase(option)=='y'){
                listContacts();
            }else{
                homePage();
            }
    }

    public static int compareName(String name01,String name02){
        String name1=name01.toUpperCase();
        String name2=name02.toUpperCase();

        int length1=name1.length();
        int length2=name2.length();
        int minLength=length2;
        if(length1<length2){
            minLength=length1;
        }

        for (int i = 0; i < minLength; i++) {
            char c1=name1.charAt(i);
            char c2=name2.charAt(i);

            if(c1!=c2){
                return c1-c2;
            }
        }
        return length1-length2;
    }

    //Sorting by Salary
    public static void sortBySalary(){
        String[] idSortBySalary=new String[idArray.length];
        for (int i = 0; i < idSortBySalary.length; i++) {
            idSortBySalary[i]=idArray[i];
        }

        for (int i = 0; i < idArray.length-1; i++) {
            for (int j = 0; j < idSortBySalary.length-1-i; j++) {
                int index1=search(idSortBySalary[j]);
                int index2=search(idSortBySalary[j+1]);
                if(salaryArray[index1]>salaryArray[index2]){
                    String temp=idSortBySalary[j];
                    idSortBySalary[j]=idSortBySalary[j+1];
                    idSortBySalary[j+1]=temp;
                }
            }
        }

        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println("\t\t|                   LIST Contact by Salary                 |");
        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println();
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-12s | %-10s | %-10s | %-10s |\n","Contact ID","Name","Phone Number","Company","Salary","Birthday");
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");
        
        int j;
        for (int i = 0; i < idSortBySalary.length; i++) {
            j=search(idSortBySalary[i]);

            System.out.printf("| %-10s | %-15s | %-12s | %-10s | %10.2f | %-10s |\n",idArray[j],nameArray[j],phoneNumberArray[j],companyNameArray[j],salaryArray[j],birthDayArray[j]);
        }
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");

         System.out.print("Do you want to list a another list (Y/N) : ");
            char option=scanner.next().charAt(0);
            if(Character.toLowerCase(option)=='y'){
                listContacts();
            }else{
                homePage();
            }
    }

    //Sorting by Birthday
    public static void sortByBirthday(){
        String[] idSortByBirthday=new String[idArray.length];
        for (int i = 0; i < idSortByBirthday.length; i++) {
            idSortByBirthday[i]=idArray[i];
        }

        for (int i = 0; i < idSortByBirthday.length - 1; i++) {
    for (int j = 0; j < idSortByBirthday.length - 1 - i; j++) {
        int idx1 = search(idSortByBirthday[j]);
        int idx2 = search(idSortByBirthday[j+1]);

        int year1  = Integer.parseInt(birthDayArray[idx1].substring(0,4));
        int month1 = Integer.parseInt(birthDayArray[idx1].substring(5,7));
        int day1   = Integer.parseInt(birthDayArray[idx1].substring(8));

        int year2  = Integer.parseInt(birthDayArray[idx2].substring(0,4));
        int month2 = Integer.parseInt(birthDayArray[idx2].substring(5,7));
        int day2   = Integer.parseInt(birthDayArray[idx2].substring(8));

        if (year1 > year2 || (year1 == year2 && month1 > month2) || (year1 == year2 && month1 == month2 && day1 > day2)) {
            String temp = idSortByBirthday[j];
            idSortByBirthday[j] = idSortByBirthday[j+1];
            idSortByBirthday[j+1] = temp;
        }
    }
}

        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println("\t\t|                  LIST Contact by Birthday                |");
        System.out.println("\t\t+----------------------------------------------------------+");
        System.out.println();
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-12s | %-10s | %-10s | %-10s |\n","Contact ID","Name","Phone Number","Company","Salary","Birthday");
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");

        int j;
        for (int i = 0; i < idSortByBirthday.length; i++) {
            j=search(idSortByBirthday[i]);

            System.out.printf("| %-10s | %-15s | %-12s | %-10s | %10.2f | %-10s |\n",idArray[j],nameArray[j],phoneNumberArray[j],companyNameArray[j],salaryArray[j],birthDayArray[j]);
        }
        System.out.println("+------------+-----------------+--------------+------------+------------+------------+");

         System.out.print("Do you want to list a another list (Y/N) : ");
            char option=scanner.next().charAt(0);
            if(Character.toLowerCase(option)=='y'){
                listContacts();
            }else{
                homePage();
            }
    }
    

    //------------------------------------------HELPERS------------------------------------------------

    private static int search(String str){
        for (int i = 0; i < nameArray.length; i++) {
            if (nameArray[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        for (int i = 0; i < phoneNumberArray.length; i++) {
            if (phoneNumberArray[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        for (int i = 0; i < idArray.length; i++) {
            if (idArray[i].equalsIgnoreCase(str)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean birthDayValidation(String birthDay){
        int year=Integer.parseInt(birthDay.substring(0,4));
        int month= Integer.parseInt(birthDay.substring(5, 7));
        int day= Integer.parseInt(birthDay.substring(8));

        LocalDate today=LocalDate.now();
        int currentYear=today.getYear();
        int currentMonth=today.getMonthValue();
        int currentDay=today.getDayOfMonth();

        //leap year check
        boolean isLeapYear;
        if (year%4==0) {
            if (year%100==0) {
                if (year%400==0) {
                    isLeapYear=true;
                }else{
                    isLeapYear=false;
                }
            }else{
                isLeapYear=true;
            }
        }else{
            isLeapYear=false;
        }

        // days of each month
        int[] daysInMonth={31,(isLeapYear? 29:28),31,30,31,30,31,31,30,31,30,31};

        if(month<0 || month>12){
            return false;
        }

        if (day<0 || day>daysInMonth[month-1]) {
            return false;
        }

        if(year>currentYear){
            return false;
        }else if (year==currentYear) {
            if (month>currentMonth) {
                return false;
            }else if(month==currentMonth && day>currentDay){
                return false;
            }
        }
        return true;
    }

    //phone number validation
    private static boolean isValidPhoneNumber(String phonenNumber){
        for (int i = 0; i < phonenNumber.length(); i++) {
            if(!Character.isDigit(phonenNumber.charAt(i))){
                return false;
            }
        }
        if(phonenNumber.charAt(0)!='0'||phonenNumber.length()!=10){
            return false;
        }
        return true;
    }

    //to extend arrys
    private final static void extendArrays(){
        int length=idArray.length;

        String[] temporaryIdArray=new String[length+1];
        String[] temporaryNameArray=new String[length+1];
        String[] temporaryPhoneNumberArray=new String[length+1];
        String[] temporaryCompanyNameArray=new String[length+1];
        double[] temporarySalaryArray=new double[length+1];
        String[] temporaryBirthDayArray=new String[length+1];

        for (int i = 0; i < length; i++) {
            temporaryIdArray[i]=idArray[i];
            temporaryNameArray[i]=nameArray[i];
            temporaryPhoneNumberArray[i]=phoneNumberArray[i];
            temporaryCompanyNameArray[i]=companyNameArray[i];
            temporarySalaryArray[i]=salaryArray[i];
            temporaryBirthDayArray[i]=birthDayArray[i];
        }

        idArray=temporaryIdArray;
        nameArray=temporaryNameArray;
        phoneNumberArray=temporaryPhoneNumberArray;
        companyNameArray=temporaryCompanyNameArray;
        salaryArray=temporarySalaryArray;
        birthDayArray=temporaryBirthDayArray;
    }

    private final static void slowProgram(){
        try {
            Thread.sleep(2000); // pause for 2 seconds (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();// Handle any exceptions.
        }
    }
    public static void main(String[] args) {
        homePage();
    }
}


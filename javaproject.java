//Author :- Mohan Kumar
//Roll No :- 100523733057
import java.util.*;
import java.io.*;
class javaproject{
    //Checks if a year is a leap year or not
    static boolean leapYear(int year){
        //checks if year is a leap year
        if(year%4==0 && (year%100!=0 || year%400==0)){
            //returns true if it is a leap year
            return true;
        }
        //returns false if it is not a leap year
        return false;
    }
    //Checks if a date is a valid date or not
    static boolean validDate(int day,int month,int year){
        //switchs based on month
        switch(month){
            //checks for months having 31 days
            case 1,3,5,7,8,10,12:
                if(day>31 || day < 1 || year<=0){
                    return false;
                }
                break;
            //checks for febreuary month
            case 2:
                //if leap year it checks if day does not exceed 29
                if(leapYear(year)&&(day>29 || day < 1 || year<=0)){
                    return false;
                }
                //if not leap year then it checks if day does not exceed 28
                else if(day>28 || day < 1 || year<=0){
                    return false;
                }
            //checks for months having 30 days
            case 4,6,9,11:
                if(day>30 || day<1 || year<=0){
                    return false;
                }
                break;
            default:
                //if month is given wrong returns false
                return false;
        }
        //if everthing is true then returns true
        return true;
    }
    //converts string type date to integer type
    static int[] parseDate(String day,String month,String year){
        //integer array to store date
        int [] date = new int[3];
        //converted each string type to integer type and storing in date array
        date[0] = Integer.parseInt(day);
        date[1] = Integer.parseInt(month);
        date[2] = Integer.parseInt(year);
        //returns date array of integer type
        return date;
    }
    //calculates age based on date of birth
    static int [] calculateAge(int day , int month , int year , int ref_day , int ref_month , int ref_year){
        int [] age = new int[3];
        age[0] = ref_day - day;
        age[1] = ref_month - month;
        age[2] = ref_year - year;
        if(age[0]<0){
            age[0] = age[0] + noOfDays(ref_month-1, ref_year);
            age[1]--;
        }
        if(age[1]<0){
            age[1] = age[1] + 12;
            age[2]--;
        }
        return age;
    }
    //calculates Date of birth based on age
    static int [] calculateDoB(int day , int month , int year , int ref_day , int ref_month , int ref_year){
        int [] DoB = new int [3];
        DoB[0] = ref_day - day;
        DoB[1] = ref_month - month;
        DoB[2] = ref_year - year;
        if(DoB[0]<1){
            DoB[0] = DoB[0] + noOfDays(ref_month-1, ref_year);
            DoB[1]--;
        }
        if(DoB[1]<1){
            DoB[1] = DoB[1] + 12;
            DoB[2]--;
        }
        return DoB;
    }
    //function to access number of days based on month
    static int noOfDays(int month,int year){
        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 2:
                if(leapYear(year)){
                    return 29;
                }else{
                    return 28;
                }
            case 4: case 6: case 9: case 11:
                return 30;
        }
        return 0;
    }
    public static void main(String args[]){
                Scanner sc = new Scanner(System.in);
                //declaring strings for dob,reference date,delimiter character
                String dob,refDate,dlc;
                String format; //declaring format
                //taking inputs
                System.out.print("\nEnter date of birth or Age (Ex:- AGE=0054-03-23 , DOB=19-05-2005) : ");
                dob=sc.nextLine();
                System.out.print("\nEnter reference date : ");
                refDate=sc.nextLine();
                System.out.print("\nChoose Dob format :\n1.YYYYdlcMMdlcDD"+"\n2.DDdlcMMdlcYYYY"+"\n3.MMdlcDDYYYY\n");
                format=sc.nextLine();
                System.out.print("\nInput delimiter character (dlc): ");
                dlc=sc.nextLine();
                //array to save result
                int [] age = new int[3];
                //checks if input is dob
                if(dob.startsWith("DOB")){    
                    //saves date of birth string by removing DOB in string
                    String DateOfBirth = dob.split("=")[1];
                    //splitting the strings based on dlc character
                    String [] date1 = DateOfBirth.split(dlc);
                    String [] date2 = refDate.split(dlc);
                    //integer arrays to store date of birth and reference dates
                    int [] dob_date = new int [3];
                    int [] ref_date = new int [3];
                    //checks the format of date
                    switch(format){
                        case "1":
                            //converting date from string type to integer type
                            dob_date = parseDate(date1[2], date1[1], date1[0]);
                            ref_date = parseDate(date2[2], date2[1], date2[0]);
                            break;
                        case "2":                            
                            //converting date from string type to integer type
                            dob_date = parseDate(date1[0], date1[1], date1[2]);
                            ref_date = parseDate(date2[0], date2[1], date2[2]);
                            break;
                        case "3":  
                            //converting date from string type to integer type    
                            dob_date = parseDate(date1[1], date1[0], date1[2]);
                            ref_date = parseDate(date2[1], date2[0], date2[2]);
                            break;
                        default:
                            //returns program
                            System.out.println("\nPlease select a valid format");
                            sc.close();
                            return;
                    }
                    //checks if date of birth is valid
                    if(!validDate(dob_date[0],dob_date[1], dob_date[2])){
                        System.out.println("\nPlease enter valid Date of Birth");
                        sc.close();
                        return;
                    }
                    //checks if reference date is valid
                    if(!validDate(ref_date[0],ref_date[1], ref_date[2])){
                        System.out.println("\nPlease enter valid Reference Date");
                        sc.close();
                        return;
                    }
                    //result is saved in age
                    age=calculateAge(dob_date[0], dob_date[1], dob_date[2], ref_date[0], ref_date[1], ref_date[2]);
                    if(age[2]<0){
                        System.out.println("\nReference year is invalid");
                        sc.close();
                        return;
                    }
                    //result is printed
                    System.out.println("Age is "+age[0]+" days, "+age[1]+" months, "+age[2]+" years");
                }
                //checks if input is age
                else if(dob.startsWith("AGE")){
                    //saves age string by removing AGE in string
                    dob = dob.split("=")[1];
                    //splitting the strings based on dlc character
                    String [] date1 = dob.split(dlc);
                    String [] date2 = refDate.split(dlc);
                    //integer arrays to store date of birth and reference dates
                    int [] dob_date = new int [3];
                    int [] ref_date = new int [3];
                    //converting date from string type to integer type
                    dob_date = parseDate(date1[2], date1[1], date1[0]);
                    //checks the format of date
                    switch(format){
                        case "1":
                            //converting date from string type to integer type    
                            ref_date = parseDate(date2[2], date2[1], date2[0]);
                            break;
                        case "2":
                            //converting date from string type to integer type    
                            ref_date = parseDate(date2[0], date2[1], date2[2]);
                            break;
                        case "3": 
                            //converting date from string type to integer type    
                            ref_date = parseDate(date2[1], date2[0], date2[2]);
                            break;
                        default:
                            //returns program
                            System.out.println("\nPlease select a valid format");
                            sc.close();
                            return;
                    }
                    //checks if age is wrong
                    if(dob_date[0]<=0 && dob_date[1]<=0 && dob_date[2]<=0){
                        System.out.println("\nPlease enter valid age");
                        sc.close();
                        return;
                    }
                    //checks if reference date is valid
                    if(!validDate(ref_date[0],ref_date[1], ref_date[2])){
                        System.out.println("\nPlease enter valid Reference Date");
                        sc.close();
                        return;
                    }
                    //result is saved in age array
                    age=calculateDoB(dob_date[0],dob_date[1],dob_date[2], ref_date[0], ref_date[1],ref_date[2]);
                    //result is printed
                    System.out.println("Date of Birth is "+age[0]+dlc+age[1]+dlc+age[2]+" ");
                }
                //if input is not age or dob then shows error
                else{
                    //returns program
                    System.out.println("\nPlease enter either in DOB=YYYYdlcMMdlcDD or AGE=00YYdlcMMdlcDD");
                    sc.close();
                    return;
                }
                sc.close();
                return;
    }
}

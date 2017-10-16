import java.util.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;

public class project
{
  public static void main(String[] args) throws IOException
  {
    String username, usernameBeforeConversion, password, firstName = "", lastName = "";    int count = 0;
    int tries = 1;
    boolean flag = false;
    double minutes;
    Date date = new Date();
    String stringDate = date.toString();
    String convertDate = stringDate.replaceAll(":","_");
// Asks for username and password
    usernameBeforeConversion = JOptionPane.showInputDialog("Username: ");
    password = JOptionPane.showInputDialog("Password: ");
    username = usernameBeforeConversion.toLowerCase();
    
// Open files 
    File file = new File("UserInfo.txt");
    Scanner inputFile = new Scanner(file);
    String[][] userFile = new String[32][6];
    for(int rows = 0; rows < 32; rows++)
    {
     for(int columns = 0; columns < 6; columns++)
     {
      userFile[rows][columns] = inputFile.next(); 
     }
    }
    
    File testfile = new File("TestBank.txt");
    Scanner testFile = new Scanner(file);
    
//Searches file for username and password 
     for(int length = 1; length < 32; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      flag = true;
      break;
     }
    }
    
    if(flag != true)
    {
     while(tries < 3)
     {
      JOptionPane.showMessageDialog(null, "Incorrect Username/Password.");
      username = JOptionPane.showInputDialog("Username: ");
      password = JOptionPane.showInputDialog("Password: ");
      tries++;
      for(int length = 1; length < 32; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      flag = true;
     }
      }
      if(flag == true)
        break;
       
      
     }
       if(tries == 3 && flag != true)
      {
        System.out.println("Username/Password is incorrect.\nProgram terminated!");
        System.exit(0); 
      }
    }
    
//Starts time
 long tStart = System.currentTimeMillis();
 
//count is returned from the Random Selection method
 count = RandomSelection();
     
     inputFile.close();
     
//Time ends
     long tEnd = System.currentTimeMillis();
long tDelta = tEnd - tStart;
double seconds = tDelta / 1000.0;

//User First and Last Name
     
     for(int length = 1; length < 32; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      firstName = userFile[length][2];
      lastName = userFile[length][3];
      break;
     }
    }
     
//Prints the report onto a file and onto the output screen
     PrintReport(username, firstName, lastName, convertDate, count, seconds);
     PrintOnScreen(firstName, lastName, count, seconds);
     
//Restarts the program
     usernameBeforeConversion = JOptionPane.showInputDialog("Username: ");
     username = usernameBeforeConversion.toLowerCase();
     while(!username.equals("done"))
     {
       password = JOptionPane.showInputDialog("Password: ");
       for(int length = 1; length < 32; length++)
        {
         if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
          {
           flag = true;
           break;
          }
        }
       if(flag != true)
    {
     while(tries < 3)
     {
      JOptionPane.showMessageDialog(null, "Incorrect Username/Password.");
      username = JOptionPane.showInputDialog("Username: ");
      password = JOptionPane.showInputDialog("Password: ");
      tries++;
      for(int length = 1; length < 32; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      flag = true;
     }
      }
      if(flag == true)
        break;
       
      
     }
       if(tries == 3 && flag != true)
      {
        System.out.println("Username/Password is incorrect.\nProgram terminated!");
        System.exit(0); 
      }
    }
  tStart = System.currentTimeMillis();
 count = RandomSelection();
     
     inputFile.close();
     tEnd = System.currentTimeMillis();
 tDelta = tEnd - tStart;
 seconds = tDelta / 1000.0;


     
     for(int length = 1; length < 32; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      firstName = userFile[length][2];
      lastName = userFile[length][3];
      break;
     }
    }
     
     PrintReport(username, firstName, lastName, convertDate, count, seconds);
     PrintOnScreen(firstName, lastName, count, seconds);
     
     
    
     usernameBeforeConversion = JOptionPane.showInputDialog("Username: ");
     username = usernameBeforeConversion.toLowerCase();
     }
     
     

inputFile.close();
System.exit(0);

  }
  

//Reads test and answer files. Gets user answers and compares them to correct answers. Returns how many were right   
  public static int RandomSelection() throws IOException
     {
       String[] testBankArr = new String[125];
       String[] ansGiven = new String[10];
       String[] correctAns = new String[10];
       String[] ansChoice = new String[125];
       int i = 0;
       int x = 0;
       int count = 0;
       String comparison;
       Random rand = new Random();
       File testBankFile = new File ("TestBank.txt");
       File answerSheetFile = new File ("Answers.txt");
       Scanner testFile = new Scanner(testBankFile);
       Scanner answerFile = new Scanner(answerSheetFile);
       
//Puts test file into an array
       while (testFile.hasNextLine() && i < testBankArr.length)
       {
         testBankArr[i] = testFile.nextLine();
         i++;
       }
       
//Puts answer file into an array
       while (answerFile.hasNextLine() && x < ansChoice.length)
         {
           ansChoice[x] = answerFile.nextLine();
           x++;
         }
      
//Gets user answers and the correct answer for the randomly selected question
       for (int index = 0; index < 10; index++)
       {
         int questionNo = rand.nextInt(125);
         ansGiven[index] = JOptionPane.showInputDialog("Question #" + (index+1) + ": " 
                                       + testBankArr[questionNo]);
     correctAns[index] = ansChoice[questionNo];
        
     }
       
//Compares answer given to the correct answer. Increments count if the answer is correct. Returns how many answers were correct
       String[] ansConvert = new String[10];
   for(int num = 0; num < 10; num++)
   {
     comparison = correctAns[num];
   ansConvert[num] = ansGiven[num].toUpperCase();
   if(ansConvert[num].equals("T"))
   {
     ansConvert[num] = "TRUE";
     if(ansConvert[num].equals(comparison))
   {
     count++;
   }
   }
   else if(ansConvert[num].equals("F"))
   {
     ansConvert[num] = "FALSE";
     if(ansConvert[num].equals(comparison))
   {
     count++;
   }
   }
   else if(ansConvert[num].equals(comparison))
   {
     count++;
   }
   }
   return count;
     }
 
  
//Prints the data onto a file  
  public static void PrintReport(String username, String firstName, String lastName, String date, int count, double seconds) throws IOException
    {
       PrintWriter outputFile = new PrintWriter(username + "_COSC_236_Quiz_"  + date + ".txt");
       outputFile.println("First name: " + firstName);
       outputFile.println("Last name: " + lastName);
       outputFile.println("You got " + count + " correct.");
       outputFile.println("Minutes: " + (((int)seconds) / 60) + "      " + "Seconds: " + ((int)seconds)%60);
       outputFile.print("User's Answers: ");
       outputFile.print("Correct Answers: ");
       outputFile.close();
    } 
    
//Prints the data on the output screen
    public static void PrintOnScreen(String firstName, String lastName, int count, double seconds)throws IOException
    {
       System.out.println("First name: " + firstName);
       System.out.println("Last name: " + lastName);
       System.out.println("You got " + count + " correct.");
       System.out.println("Minutes: " + (((int)seconds) / 60) + "      " + "Seconds: " + ((int)seconds)%60);
       System.out.print("User's Answers: ");
       System.out.print("Correct Answers: ");
    }
}

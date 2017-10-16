import java.util.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;

public class Project
{
  public static void main(String[] args) throws IOException
  {
    String username, usernameBeforeConversion, password, firstName = "", lastName = "";
    int count = 0;
    int tries = 1;
    boolean flag = false;
    double minutes;
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
     for(int length = 1; length < userFile.length; length++)
    {
     if(userFile[length][0].equals(username) && userFile[length][1].equals(password))
     {
      flag = true;
      if(userFile[length][5].equals("Student"))
      {
        break;
      }
      else if(userFile[length][5].equals("Instructor"))
      {
       InstructorActions();
       return;
      }
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
      for(int length = 1; length < userFile.length; length++)
    {
     if(userFile[length][0].equals(username.toLowerCase()) && userFile[length][1].equals(password))
     {
      flag = true;
      if(userFile[length][5].equals("Student"))
      {
        break;
      }
      else if(userFile[length][5].equals("Instructor"))
      {
       InstructorActions();
       return;
      }
      break;
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
    

 
//Random Selection method
 RandomSelection(username, firstName, lastName);
     
 inputFile.close();
     


   

     
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
           for(int row = 1; row < userFile.length; row++)
    {
     if(userFile[row][0].equals(username) && userFile[row][1].equals(password))
     {
      flag = true;
      if(userFile[row][5].equals("Student"))
      {
        break;
      }
      else if(userFile[row][5].equals("Instructor"))
      {
       InstructorActions();
       return;
      }
      break;
     }
    }
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
     if(userFile[length][0].equals(username.toLowerCase()) && userFile[length][1].equals(password))
     {
      flag = true;
      if(userFile[length][5].equals("Student"))
      {
        break;
      }
      else if(userFile[length][5].equals("Instructor"))
      {
       InstructorActions();
       return;
      }
      break;
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
       
       RandomSelection(username, firstName, lastName);
     
     inputFile.close();



     
     
     
     
     usernameBeforeConversion = JOptionPane.showInputDialog("Username: ");
     username = usernameBeforeConversion.toLowerCase();
     }
     
     

inputFile.close();
return;

  }
  

//Reads test and answer files. Gets user answers and compares them to correct answers. Returns how many were right   
  public static void RandomSelection(String username, String firstName, String lastName) throws IOException
  {
    String nameUser = username;
    String nameFirst = firstName;
    String nameLast = lastName;
    Date date = new Date();
    String stringDate = date.toString();
    String convertDate = stringDate.replaceAll(":","_");
       String[] testBankArr = new String[125];
       String[] ansGiven = new String[10];
       String[] correctAns = new String[10];
       String[] ansChoice = new String[125];
       String[] testArr = new String[10];
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
//Start time
       long tStart = System.currentTimeMillis();
       
//Gets user answers and the correct answer for the randomly selected question
       for (int index = 0; index < 10; index++)
       {
         int questionNo = rand.nextInt(125);
         testArr[index] = testBankArr[questionNo];
         ansGiven[index] = JOptionPane.showInputDialog("Question #" + (index+1) + ": " 
                                       + testBankArr[questionNo] + " (True or False Answers Only)");
         //while(!ansGiven[index].equals("
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
   
   long tEnd = System.currentTimeMillis();
   long tDelta = tEnd - tStart;
   double seconds = tDelta / 1000.0;
  JOptionPane.showMessageDialog(null, "Questions     User Answers     Correct Answers\n" + 
                             testArr[0] + "     " + ansConvert[0] + "     " + correctAns[0] + "\n" +
                             testArr[1] + "     " + ansConvert[1] + "     " + correctAns[1] + "\n" +
                             testArr[2] + "     " + ansConvert[2] + "     " + correctAns[2] + "\n" +
                             testArr[3] + "     " + ansConvert[3] + "     " + correctAns[3] + "\n" +
                             testArr[4] + "     " + ansConvert[4] + "     " + correctAns[4] + "\n" +
                             testArr[5] + "     " + ansConvert[5] + "     " + correctAns[5] + "\n" +
                             testArr[6] + "     " + ansConvert[6] + "     " + correctAns[6] + "\n" +
                             testArr[7] + "     " + ansConvert[7] + "     " + correctAns[7] + "\n" +
                             testArr[8] + "     " + ansConvert[8] + "     " + correctAns[8] + "\n" +
                             testArr[9] + "     " + ansConvert[9] + "     " + correctAns[9], "Report", JOptionPane.PLAIN_MESSAGE);
  
  PrintReport(nameUser, nameFirst, nameLast, convertDate, count, seconds, testArr, ansConvert, correctAns);
     PrintOnScreen(nameFirst, nameLast, count, seconds, testArr, ansConvert, correctAns);
     PrintReportForInstructor(nameFirst, nameLast, count, seconds);
     }
  
  //The instructor has three options on how to manipulate information regarding the test.
  public static void InstructorActions()throws IOException
  {
    Scanner instructorInput = new Scanner(System.in);
    String[] userFileAddOn = new String[6];
    String letters = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    int cases;
   FileWriter userFileWriter = new FileWriter("UserInfo.txt", true);
   FileWriter testBankWriter = new FileWriter("TestBank.txt", true);
   FileWriter answerWriter = new FileWriter("Answers.txt", true);
   PrintWriter appendUserFile = new PrintWriter(userFileWriter);
   PrintWriter appendTestBankFile = new PrintWriter(testBankWriter);
   PrintWriter appendAnswerFile = new PrintWriter(answerWriter);
   Random rand = new Random();
   String oneLetter = "";
   String actions;
   String actions1;
   cases = Integer.parseInt(JOptionPane.showInputDialog("Enter the number that corresponds to which action you want to take.\n1. Register a new student.\n2. Display stats.\n3. Add new questions.\n4. Done"));
   while(cases != 4)
     {
   switch (cases)
   {
     //Case 1 allows the instructor input information for a new student.
     case 1: 
       JOptionPane.showMessageDialog(null, "New Student Registration");
       actions = JOptionPane.showInputDialog("Student First Name:");
       userFileAddOn[2] = actions;
       actions = JOptionPane.showInputDialog("Student Last Name:");
       userFileAddOn[3] = actions;
       actions = JOptionPane.showInputDialog("Student Username:");
       userFileAddOn[0] = actions;
       userFileAddOn[4] = actions + "@students.towson.edu";
       userFileAddOn[5] = "Student";
       for(int x = 0; x < 6; x++)
       {
         oneLetter += letters.charAt(rand.nextInt(26)) + "";
       }
       userFileAddOn[1] = oneLetter;
       appendUserFile.println("");
       for(int index = 0; index < 6; index++)
       {
         if(index == 5)
        {
          appendUserFile.print(userFileAddOn[index] + "\n");
        }
        else
        {
          appendUserFile.print(userFileAddOn[index] + "\t");
        }
       }
       appendUserFile.close();
       break;
     /*Case 2 takes information from the InstructorReport.txt file and uses it to find and display
      * the students who had the shortest time, lowest grade, highest grade, and the average class
      * grade*/
     case 2:
       File instructorFile = new File("InstructorReport.txt");
       Scanner instructorOutput = new Scanner(instructorFile);
       ArrayList<String> list = new ArrayList<String>();
       String listAdd;
       int sum = 0;
       int lowestGrade = 11;
       int highestGrade = -1;
       int shortestSeconds = Integer.MAX_VALUE;
       String secondsStudentFirstName = "", secondsStudentLastName = "", studentLowFirstName = "", studentLowLastName = "", studentHighFirstName = "", studentHighLastName = "";
       while(instructorOutput.hasNextLine())
       {
         listAdd = instructorOutput.nextLine();
         list.add(listAdd);
       }
       String[][] arrList = new String[(list.size())/4][4];
       
       int i = 0;
       
       for(int row = 0; row < ((list.size())/4); row++)
       {
         for(int column = 0; column < 4; column++)
         {
           
           arrList[row][column] = list.get(i);
           i++;
         }
       }
       for(int row = 0; row < (list.size())/4; row++)
       {
         if(shortestSeconds > Integer.parseInt(arrList[row][3]))
         {
           shortestSeconds = Integer.parseInt(arrList[row][3]);
           secondsStudentFirstName = arrList[row][0];
           secondsStudentLastName = arrList[row][1];
         }
       } 
       for(int row = 0; row < (list.size())/4; row++)
       {
         if(lowestGrade > Integer.parseInt(arrList[row][2]))
         {
           lowestGrade = Integer.parseInt(arrList[row][2]);
           studentLowFirstName = arrList[row][0];
        studentLowLastName = arrList[row][1];
      }
    } 
    for(int row = 0; row < (list.size())/4; row++)
    {
      if(highestGrade < Integer.parseInt(arrList[row][2]))
      {
        highestGrade = Integer.parseInt(arrList[row][2]);
        studentHighFirstName = arrList[row][0];
        studentHighLastName = arrList[row][1];
      }
    } 
    
    
    for(int row = 0; row < (list.size())/4; row++)
    {
      
      
        sum += Integer.parseInt(arrList[row][2]);
      
    } 
    System.out.println("Student with the shortest time : " + secondsStudentFirstName + " " + secondsStudentLastName + " with " + shortestSeconds + " seconds");
    System.out.println("Student with the lowest grade: " + studentLowFirstName + " " + studentLowLastName + " with a grade of " + lowestGrade * 10 + "%");
    System.out.println("Student with the highest grade: " + studentHighFirstName + " " + studentHighLastName + " with a grade of " + highestGrade * 10 + "%");
    System.out.printf("The average of the class is %.2f",((double)(sum / (list.size()/4.0))*10.0));
    System.out.println("%");
       break;
       /*Case 3 allows the instructor to add more questions to the test bank file and their respective 
        * and their respective answers are put into the answers file. The instructor could input
        * questions manually or through a text file. The text file they input must contain no space
        * and they do not need to inplement the .txt at the end to the file name.*/
     case 3:
       actions = JOptionPane.showInputDialog("Do you have a file for the new questions?\nAnswer only as Yes or No.");
       if(actions.toUpperCase().equals("NO"))
       {
         actions = JOptionPane.showInputDialog("Question:");
         appendTestBankFile.print("\n");
         appendTestBankFile.print(actions);
         actions = JOptionPane.showInputDialog("Answer:");
         appendAnswerFile.print("\n");
         appendAnswerFile.print(actions.toUpperCase());
         actions = JOptionPane.showInputDialog("Question:\nIf done, enter done.");
         while(!actions.toUpperCase().equals("DONE"))
         {
          appendTestBankFile.print("\n" + actions);
          actions = JOptionPane.showInputDialog("Answer:");
          appendAnswerFile.print("\n" + actions.toUpperCase());
          actions = JOptionPane.showInputDialog("Question:\nIf done, enter done.");
          }
         }
       else if(actions.toUpperCase().equals("YES"))
       {
         actions = JOptionPane.showInputDialog("What is the name of the file?");
         File testAnswerFile = new File(actions + ".txt");
         Scanner testAnswerInput = new Scanner(testAnswerFile);
         int index = 0;
         appendTestBankFile.print("\n");
         appendAnswerFile.print("\n");
         while(testAnswerInput.hasNextLine())
         {
           for(String splitLine : testAnswerInput.nextLine().split("\t"))
           {
             if(index % 2 == 0)
           {
            appendTestBankFile.println(splitLine);
           }
           else if(index % 2 == 1)
           {
            appendAnswerFile.println(splitLine.toUpperCase());
            
           }
           index++;
             
           }
         }
         
       }
       else
       {
         while(!actions.toUpperCase().equals("YES") && !actions.toUpperCase().equals("NO") && !actions.toUpperCase().equals("DONE"))
         {
           JOptionPane.showMessageDialog(null, "Invalid response");
           actions = JOptionPane.showInputDialog("Do you have a file for the new questions?\nAnswer only as Yes or No.");
       if(actions.toUpperCase().equals("NO"))
       {
         actions1 = JOptionPane.showInputDialog("Question:");
         appendTestBankFile.print("\n");
         appendTestBankFile.print(actions1);
         actions1 = JOptionPane.showInputDialog("Answer:");
         appendAnswerFile.print("\n");
         appendAnswerFile.print(actions1.toUpperCase());
         actions1 = JOptionPane.showInputDialog("Question:\nIf done, enter done.");
         while(!actions1.toUpperCase().equals("DONE"))
         {
          appendTestBankFile.print("\n");
          appendTestBankFile.print(actions1);
          actions1 = JOptionPane.showInputDialog("Answer:");
          appendAnswerFile.print("\n");
          appendAnswerFile.print(actions1.toUpperCase());
          actions1 = JOptionPane.showInputDialog("Question:\nIf done, enter done.");
          }
         }
       else if(actions.toUpperCase().equals("YES"))
       {
         actions1 = JOptionPane.showInputDialog("What is the name of the file?");
         File testAnswerFile = new File(actions1 + ".txt");
         Scanner testAnswerInput = new Scanner(testAnswerFile);
         int index = 0;
         appendTestBankFile.print("\n");
         appendAnswerFile.print("\n");
         while(testAnswerInput.hasNextLine())
         {
           for(String splitLine : testAnswerInput.nextLine().split("\t"))
           {
             if(index % 2 == 0)
           {
            appendTestBankFile.print(splitLine);
           
           }
           else if(index % 2 == 1)
           {
            appendAnswerFile.print(splitLine.toUpperCase());
            
           }
           index++;
             
           }
         }
         
       }
         }
        
       }
       appendTestBankFile.close();
       appendAnswerFile.close();
       break;
   }
   cases = Integer.parseInt(JOptionPane.showInputDialog("Enter the number that corresponds to which action you want to take.\n1. Register a new student.\n2. Display stats.\n3. Add new questions.\n4. Done"));
  }
  }
  
 
  
//Prints the data onto a file  
  public static void PrintReport(String username, String firstName, String lastName, String date, int count, double seconds, String[] test, String[] ans, String[] correct) throws IOException
    {
       PrintWriter outputFile = new PrintWriter(username + "_COSC_236_Quiz_"  + date.replace(" ", "_") + ".txt");
       outputFile.println("First name: " + firstName);
       outputFile.println("Last name: " + lastName);
       outputFile.println("You got " + count + " correct.");
       outputFile.println("Minutes: " + (((int)seconds) / 60) + "      " + "Seconds: " + ((int)seconds)%60);
       outputFile.print("Questions     User Answers     Correct Answers\n" + 
                             test[0] + "     " + ans[0] + "     " + correct[0] + "\n" +
                             test[1] + "     " + ans[1] + "     " + correct[1] + "\n" +
                             test[2] + "     " + ans[2] + "     " + correct[2] + "\n" +
                             test[3] + "     " + ans[3] + "     " + correct[3] + "\n" +
                             test[4] + "     " + ans[4] + "     " + correct[4] + "\n" +
                             test[5] + "     " + ans[5] + "     " + correct[5] + "\n" +
                             test[6] + "     " + ans[6] + "     " + correct[6] + "\n" +
                             test[7] + "     " + ans[7] + "     " + correct[7] + "\n" +
                             test[8] + "     " + ans[8] + "     " + correct[8] + "\n" +
                             test[9] + "     " + ans[9] + "     " + correct[9]);
      
       outputFile.close();
    } 
  
  public static void PrintReportForInstructor(String firstName, String lastName, int count, double seconds) throws IOException
  {
       FileWriter instructorFile = new FileWriter("InstructorReport.txt", true);
       PrintWriter outputFile = new PrintWriter(instructorFile);
       outputFile.println(firstName);
       outputFile.println(lastName);
       outputFile.println(count);
       outputFile.println(((int)seconds));
       outputFile.close();
    }
  
    
//Prints the data on the output screen
    public static void PrintOnScreen(String firstName, String lastName, int count, double seconds, String[] test, String[] ans, String[] correct )throws IOException
    {
       System.out.println("First name: " + firstName);
       System.out.println("Last name: " + lastName);
       System.out.println("You got " + count + " correct.");
       System.out.println("Minutes: " + (((int)seconds) / 60) + "      " + "Seconds: " + ((int)seconds)%60);
       System.out.println("Questions     User Answers     Correct Answers\n" + 
                             test[0] + "     " + ans[0] + "     " + correct[0] + "\n" +
                             test[1] + "     " + ans[1] + "     " + correct[1] + "\n" +
                             test[2] + "     " + ans[2] + "     " + correct[2] + "\n" +
                             test[3] + "     " + ans[3] + "     " + correct[3] + "\n" +
                             test[4] + "     " + ans[4] + "     " + correct[4] + "\n" +
                             test[5] + "     " + ans[5] + "     " + correct[5] + "\n" +
                             test[6] + "     " + ans[6] + "     " + correct[6] + "\n" +
                             test[7] + "     " + ans[7] + "     " + correct[7] + "\n" +
                             test[8] + "     " + ans[8] + "     " + correct[8] + "\n" +
                             test[9] + "     " + ans[9] + "     " + correct[9]);
       
    }
}
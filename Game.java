import java.util.*;  // Needed for Scanner Class
import java.io.*;  // Needed for File I/O

public class Game {

  public static void main (String[] args) throws IOException {

    // intializes temporary variables
    String fName = "";
    String lName = "";
    double betMoney = 0.0;
    double moneyWon = 0.0;
    double remainMoney = 0.0;
    int countPlay = 0;
    int countWon = 0;

    do {
      // Creates new class objects
      Scanner inputScan = new Scanner(System.in);
      Player player = new Player();

      // Introduction
      System.out.println("Welcome to Over and Under!\n");
      System.out.print("Please enter your first name: ");

      // takes in users first name
      fName = inputScan.nextLine();

      // used as reference for temp variables
      player.setFirstName(fName);
      player.setLastName(lName);
      player.remainingMoney(remainMoney);

      // checks to see if file exists
      File outputFile = new File(player.getFirstName() + ".txt");
      if (!outputFile.exists())
      {
        PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");  // creates player text file

        // Welcomes player and asks for player information
        System.out.println("\nWelcome New Player!");
        outFile.println(player.getFirstName().toString());
        System.out.println("\nPlease enter your information below: ");
        System.out.print("Last Name: ");
        lName = inputScan.nextLine();
        player.setLastName(lName);
        outFile.println(player.getLastName().toString());
        System.out.print("Initial Money: $");
        remainMoney = inputScan.nextDouble();
        outFile.println(player.remainingMoney(remainMoney));
        outFile.close();

        // Displays players information
        System.out.println("\nHere is your current information:");
        System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
        System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
        System.out.println("Total Rounds Played: " + player.getNumPlays());
        System.out.println("Total Rounds Won: " + player.getNumWons());
      } else {  // executes when player file is recognized

        // Displays players information
        System.out.println("\nWelcome back " + player.getFirstName() + "!!");

        //Opens existing file
        outputFile = new File(player.getFirstName() + ".txt");
        Scanner inputFile = new Scanner(outputFile);

        //Reads lines from file
        String firstName = inputFile.nextLine();
        player.setFirstName(firstName);
        String lastName = inputFile.nextLine();
        player.setLastName(lastName);
        double moneyRemain = inputFile.nextDouble();
        player.remainingMoney(moneyRemain);
        int gamesPlayed = inputFile.nextInt();
        gamesPlayed = player.numRoundsWon();
        int gamesWon = inputFile.nextInt();
        gamesWon = player.numRoundsWon();


        System.out.println("\nHere is your current information:");
        System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
        System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
        System.out.println("Total Rounds Played: " + player.getNumPlays());
        System.out.println("Total Rounds Won: " + player.getNumWons());

        // If user does not have any money in his account
        if(player.getMoney() <= 0){
          System.out.println("\n\nWe're sorry but you have no money to play!");
          System.out.println("\nThanks for playing!");
          System.out.println("\nHere is your updated information:");
          System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
          System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
          System.out.println("Total Rounds Played: " + player.getNumPlays());
          System.out.println("Total Rounds Won: " + player.getNumWons());
          System.out.println("----------------------------------------------------\n");

          // Writes updated information into the text file
          FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
          PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

          outFile.println(player.getFirstName().toString());
          outFile.println(player.getLastName().toString());
          outFile.println(player.remainingMoney(remainMoney));
          outFile.println(player.getNumPlays());
          outFile.println(player.getNumWons());
          outFile.close();

          System.exit(0);
        }
      }


      System.out.print("\nWould you like to play a round? (Y/N)   > ");
      char userInput = inputScan.next().charAt(0);

      while(userInput != 'Y' || userInput != 'N'){

        if(userInput == 'Y'){
          System.out.printf("How much money would you like to bet?  (<=$" + player.getMoney() + "): $");
          betMoney = inputScan.nextDouble();

          // will not accept money bets greater than the user value or less than zero
          while(betMoney > player.getMoney() || betMoney == 0 || betMoney < 0){
            if(betMoney > player.getMoney()){
             System.out.println("Input Invalid: Please enter a value under $" + player.getMoney());
             System.out.printf("How much money would you like to bet?  (<=$" + player.getMoney() + "): $");
             betMoney = inputScan.nextDouble();
            } else if(betMoney == 0 || betMoney < 0){
             System.out.println("Input Invalid: Please enter a value over zero and under $" + player.getMoney());
             System.out.printf("How much money would you like to bet?  (<=$" + player.getMoney() + "): $");
             betMoney = inputScan.nextDouble();
            } else{
            }
          } // end while


          System.out.print("\nChoose an option: (O)ver, (S)even, or (U)nder   > ");

          char choiceInput = inputScan.next().charAt(0);


          while(choiceInput != 'O' || choiceInput != 'S' || choiceInput != 'U'){
            if(choiceInput == 'O'){
              int sum = Dice();

              if(sum > 7){
                System.out.println("\nYou WON this round!\n");
                System.out.printf("You have won $%.2f%n", + betMoney*2);
                // Calculates money won
                betMoney = betMoney * 2;
                remainMoney = remainMoney + betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());

                //Keeps track of money won this round
                moneyWon = moneyWon + betMoney;

                // Keeps count of rounds played and games won
                player.numRoundsWon();
                player.numRoundsPlayed();
                countPlay++;
                countWon++;
              } else if(sum == 7){
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                //Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");

                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              } else{
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                // Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");

                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              }
              break;
            } else if(choiceInput == 'S'){
              int sum = Dice();

              if(sum == 7){
                System.out.println("\nYou WON this round!\n");
                System.out.printf("You have won $%.2f%n", + betMoney*4);
                // Calculates money won
                betMoney = betMoney * 4;
                remainMoney = remainMoney + betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());

                //Keeeps track of money won
                moneyWon = moneyWon + betMoney;

                // Keeps count of rounds played and games won
                player.numRoundsWon();
                player.numRoundsPlayed();
                countPlay++;
                countWon++;
              } else if(sum > 7){
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                // Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");

                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              } else{
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                // Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");

                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              }
              break;
            } else if(choiceInput == 'U'){
              int sum = Dice();

              if(sum < 7){
                System.out.println("\nYou WON this round!\n");
                // Calculates money won
                betMoney = betMoney * 2;
                remainMoney = remainMoney + betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());

                // Keeps track of money won
                moneyWon = moneyWon + betMoney;

                // Keeps count of rounds played and games won
                player.numRoundsWon();
                player.numRoundsPlayed();
                countPlay++;
                countWon++;
              } else if(sum > 7){
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                // Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");

                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              } else{
                System.out.println("\nSorry, you LOST this round.\n");
                System.out.printf("You have lost $%.2f%n", + betMoney);
                // Calculates money lost
                remainMoney = remainMoney - betMoney;
                player.remainingMoney(remainMoney);
                System.out.printf("Your updated money value is $%.2f%n", + player.getMoney());
                // Keeps count of rounds played
                player.numRoundsPlayed();
                countPlay++;

                // executes if player money value is zero
                if(player.getMoney() <= 0){
                 System.out.println("\n\nWe're sorry but you have no money to play!");
                 System.out.println("\nThanks for playing!");
                 System.out.println("\nHere is your updated information:");
                 System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
                 System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
                 System.out.println("Total Rounds Played: " + player.getNumPlays());
                 System.out.println("Total Rounds Won: " + player.getNumWons());
                 System.out.println();
                 System.out.printf("During this game you won $%.2f%n", + moneyWon);
                 System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
                 System.out.println("----------------------------------------------------\n");


                 // Writes updated information into the text file
                 FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
                 PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

                 outFile.println(player.getFirstName().toString());
                 outFile.println(player.getLastName().toString());
                 outFile.println(player.remainingMoney(remainMoney));
                 outFile.println(player.getNumPlays());
                 outFile.println(player.getNumWons());
                 outFile.close();

                 System.exit(0);
                }
              }
              break;
            } else {  // executes if choiceInput is invalid
              System.out.println("Invalid Input: Please enter O, S, or U.\n");
              System.out.print("Choose an option: (O)ver, (S)even, or (U)nder   > ");
              choiceInput = inputScan.next().charAt(0);
            }
          } // end while

          System.out.print("\nWould you like to play another round? (Y/N)   > ");
          userInput = inputScan.next().charAt(0);

        } else if(userInput == 'N'){
          System.out.println("\nThanks for playing!");
          System.out.println("\nHere is your updated information:");
          System.out.println("Name: " + player.getFirstName() + " " + player.getLastName());
          System.out.printf("Money Left: $" + "%.2f%n", + player.getMoney());
          System.out.println("Total Rounds Played: " + player.getNumPlays());
          System.out.println("Total Rounds Won: " + player.getNumWons());
          System.out.println();
          System.out.printf("During this game you won $%.2f%n", + moneyWon);
          System.out.println("During this game you won " + countWon + " round(s) out of " + countPlay + " total");
          System.out.println("----------------------------------------------------\n");

          // Writes updated information into the text file
          FileWriter fwriter = new FileWriter(player.getFirstName() + ".txt");
          PrintWriter outFile = new PrintWriter(player.getFirstName() + ".txt");

          outFile.println(player.getFirstName().toString());
          outFile.println(player.getLastName().toString());
          outFile.println(player.remainingMoney(remainMoney));
          outFile.println(player.getNumPlays());
          outFile.println(player.getNumWons());
          outFile.close();

          System.exit(0);
        } else {
          System.out.println("Invalid Input: Please enter Y or N.\n");
          System.out.print("Would you like to play a round? (Y/N)   > ");

          userInput = inputScan.next().charAt(0);
        }
      } // end while
    } while(true);  // end do-while
  } // end main


  // Rolls the dice and gets sum of the face values
  public static int Dice(){

    // creates two die objects
    Die die1 = new Die();
    Die die2 = new Die();

    // rolls the dice
    die1.roll();
    die2.roll();

    // totals both face values from the rolled dice
    int sum;
    sum = die1.getFaceValue() + die2.getFaceValue();

    System.out.println("\nThe dice have been rolled.....and the result is....");
    System.out.print("Die 1: " + die1.getFaceValue());
    System.out.print("\tDie 2: " + die2.getFaceValue());
    System.out.println("\t==>       Sum: " + sum);

    if(sum > 7) {
      System.out.println("\t\t.....OVER.....\n");
    } else if(sum == 7) {
      System.out.println("\t\t.....SEVEN.....\n");
    } else {
      System.out.println("\t\t.....UNDER.....\n");
    }

    return sum;
  } // end Dice()

}  // end class Game

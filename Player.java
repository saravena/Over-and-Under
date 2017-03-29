import java.util.*;
import java.io.*;

public class Player  {
  // Instance Variables
  private String firstName;
  private String lastName;
  private double remainMoney;
  private int roundsPlayed;
  private int roundsWon;

  //Constructor
  public Player() {
    firstName = "";
    lastName = "";
    remainMoney = 0.0;
    roundsPlayed = 0;
    roundsWon = 0;
  } // end Player


  // Mutators
  public String setFirstName(String Fname){
    firstName = Fname;
    return firstName;
  } // end setFirstName

  public String setLastName(String Lname){
    lastName = Lname;
    return lastName;
  } // end setLastName

  public double remainingMoney(double moneyRemain){
    remainMoney = moneyRemain;
    return remainMoney;
  } // end remainingMoney

  public int numRoundsPlayed() {
    roundsPlayed = roundsPlayed + 1;
    return roundsPlayed;
  } // end numRoundsPlayed

  public int numRoundsWon() {
    roundsWon = roundsWon + 1;
    return roundsWon;
  } // end numRoundsWon


  // Accessors
  public String getFirstName(){
    return firstName;
  } // end getLastName

  public String getLastName(){
    return lastName;
  } // end getFirsttName

  public int getNumPlays(){
    return roundsPlayed;
  } // getNumRounds

  public int getNumWons(){
    return roundsWon;
  } // end getNumWons

  public double getMoney(){
    return remainMoney;
  } // end getMoney

} // end class Player

import java.util.*;
import java.io.*;

public class Die{
  // Instance Variables
  private int MAX = 6;  // maximum face value of die
  private int faceValue;

  // Constructor
  public Die() {
    faceValue = 1;
  } // end Die

  // Accessor
  public int getFaceValue() {
    return faceValue;
  } // end getFaceValue

  // Simulates the rolling of a die
  public int roll() {
    Random rand = new Random();
    faceValue = rand.nextInt(MAX) + 1;
    return faceValue;
  } // end roll

} // end class Die

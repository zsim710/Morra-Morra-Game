package nz.ac.auckland.se281;

public class DifficultyMaker {
  
  // class is used to create different levels of difficulty of the ai, depending on the user's choice
  public static Level createCpu(String difficulty) {

    switch (difficulty) {
      case "EASY":
        return new Easy(new Random());
      case "MEDIUM":
        return new Medium(new Random());
      case "HARD":
        return new Hard(new Random());
      case "MASTER":
        return new Master(new Random());
      default:
        return null;
    }
  }
}

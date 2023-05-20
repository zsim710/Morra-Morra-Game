package nz.ac.auckland.se281;

public class difficultyMaker {


    public static level createCpu(String difficulty) {

        switch (difficulty) {
            case "EASY":
                    return new easy(new random());
            case "MEDIUM":
                    return new medium(new random());
            case "HARD":
                    return new hard(new random());
            case "MASTER":
                    return new master(new random());
            default:
                return null;    
            }
    }
}

package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  public class Player {
    private String name;
    private int fingers;
    private int sum;

    // declaring player class to store players moves and information.
    public Player(String name) {
      this.name = name;
    }

    public void setfingers(String fingers) {
      this.fingers = Integer.parseInt(fingers);
    }

    public void setSum(String sum) {
      this.sum = Integer.parseInt(sum);
    }

    public String getPlayerfingers() {
      return Integer.toString(fingers);
    }

    public String getPlayerSum() {
      return Integer.toString(sum);
    }

    public String getPlayerName() {
      return name;
    }

    public boolean isValidfingers() {
      if (fingers >= 1 && fingers <= 5) {
        return true;
      } else {
        return false;
      }
    }

    public boolean isValidSum() {
      if (sum >= 1 && sum <= 10) {
        return true;
      } else {
        return false;
      }
    }
  }

  // initalise round variable to increment rounds.
  private int round;

  // use arraylists to store players information and cpus information.
  // also store previous fingers to use in the strategies.
  private List<Player> players = new ArrayList<Player>();
  private List<Level> CPU = new ArrayList<Level>();
  private List<Integer> prevfingers = new ArrayList<Integer>();

  public Morra() {}

  boolean gameisrunning = false;
  int humanpoints = 0;
  int aipoints = 0;
  int pointstowin = 0;

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    gameisrunning = true;

    prevfingers.clear();
    players.clear();
    this.pointstowin = pointsToWin;
    round = 0;
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);
    Player player1 = new Player(options[0]);
    players.add(player1);

    Level ai = DifficultyMaker.createCpu(String.valueOf(difficulty));
    CPU.add(ai);

  }

  public void play() {

    if (!gameisrunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    round++;
    MessageCli.START_ROUND.printMessage(Integer.toString(round));

    // while loop to keep letting person enter valid fingers and sum
    while (true) {
      MessageCli.ASK_INPUT.printMessage();

      String input = Utils.scanner.nextLine();
      String[] fingerNsum = input.split(" ");

      players.get(0).setfingers(fingerNsum[0]);
      players.get(0).setSum(fingerNsum[1]);

      if (players.get(0).isValidfingers() && players.get(0).isValidSum() && fingerNsum.length == 2) {
        MessageCli.PRINT_INFO_HAND.printMessage(
            players.get(0).getPlayerName(),
            players.get(0).getPlayerfingers(),
            players.get(0).getPlayerSum());
        break;
      } else {
        MessageCli.INVALID_INPUT.printMessage();
      }
    }


    int cpufingers = CPU.get(0).getFingers();

    int cpusum = CPU.get(0).getSum();
    MessageCli.PRINT_INFO_HAND.printMessage(
        "Jarvis", Integer.toString(cpufingers), Integer.toString(cpusum));

    int actualsum = cpufingers + players.get(0).fingers;

    // conditions for settling the round.
    if (actualsum == players.get(0).sum && actualsum != cpusum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      humanpoints++;

    } else if (actualsum == cpusum && actualsum != players.get(0).sum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      aipoints++;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    }

    prevfingers.add(players.get(0).fingers);

    // conditions for ending the game.
    if (humanpoints == pointstowin) {
      MessageCli.END_GAME.printMessage(players.get(0).getPlayerName(), Integer.toString(round));
      gameisrunning = false;
      return;
    } else if (aipoints == pointstowin) {
      MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(round));
      gameisrunning = false;
      return;
    } else {
      if (round == 3 && CPU.get(0).getClass().equals(Medium.class)) {
        CPU.get(0).setStrategy(new AvgStrategy(prevfingers));
      } else if (round == 3 && (CPU.get(0).getClass().equals(Hard.class))) {
        CPU.get(0).setStrategy(new TopStrategy(prevfingers));
      } else if ((CPU.get(0).getClass().equals(Master.class) && round >= 3)) {
        if (round % 2 == 0) {
          CPU.get(0).setStrategy(new TopStrategy(prevfingers));
        } else {
          CPU.get(0).setStrategy(new AvgStrategy(prevfingers));
        }
      } else {
        return;
      }
    }
  }

  public void showStats() {

    if (!gameisrunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    } else {
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          players.get(0).getPlayerName(),
          Integer.toString(humanpoints),
          Integer.toString(pointstowin - humanpoints));
      MessageCli.PRINT_PLAYER_WINS.printMessage(
          "Jarvis", Integer.toString(aipoints), Integer.toString(pointstowin - aipoints));
    }
  }
}

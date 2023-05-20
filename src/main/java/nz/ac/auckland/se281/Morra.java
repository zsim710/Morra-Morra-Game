package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  int round;

  List<Player> players = new ArrayList<Player>();
  List<level> CPU = new ArrayList<level>();
  List<Integer> prevfingers = new ArrayList<Integer>();

  public class Player {
    private String name;
    private int Fingers;
    private int Sum;

    public Player(String name) {
      this.name = name;
    }

    public void setFingers(String fingers) {
      this.Fingers = Integer.parseInt(fingers);
    }

    public void setSum(String sum) {
      this.Sum = Integer.parseInt(sum);
    }

    public String getPlayerFingers() {
      return Integer.toString(Fingers);
    }

    public String getPlayerSum() {
      return Integer.toString(Sum);
    }

    public String getPlayerName() {
      return name;
    }

    public boolean isValidfingers() {
      if (Fingers >= 1 && Fingers <= 5) {
        return true;
      } else {
        return false;
      }
    }

    public boolean isValidsum() {
      if (Sum >= 1 && Sum <= 10) {
        return true;
      } else {
        return false;
      }
    }
  }

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

    level ai = difficultyMaker.createCpu(String.valueOf(difficulty));
    CPU.add(ai);
    // DifficultyMaker difficultyMaker = new DifficultyMaker(difficulty);

  }

  public void play() {

    if (!gameisrunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    round++;
    MessageCli.START_ROUND.printMessage(Integer.toString(round));

    while (true) {
      MessageCli.ASK_INPUT.printMessage();
      String input = Utils.scanner.nextLine();
      String[] fingerNsum = input.split(" ");

      players.get(0).setFingers(fingerNsum[0]);
      players.get(0).setSum(fingerNsum[1]);

      if (players.get(0).isValidfingers() && players.get(0).isValidsum()) {
        MessageCli.PRINT_INFO_HAND.printMessage(
            players.get(0).getPlayerName(),
            players.get(0).getPlayerFingers(),
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

    int actualsum = cpufingers + players.get(0).Fingers;

    if (actualsum == players.get(0).Sum && actualsum != cpusum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      humanpoints++;

    } else if (actualsum == cpusum && actualsum != players.get(0).Sum) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      aipoints++;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    }

    prevfingers.add(players.get(0).Fingers);

    if (humanpoints == pointstowin) {
      MessageCli.END_GAME.printMessage(players.get(0).getPlayerName(), Integer.toString(round));
      gameisrunning = false;
      return;
    } else if (aipoints == pointstowin) {
      MessageCli.END_GAME.printMessage("Jarvis", Integer.toString(round));
      gameisrunning = false;
      return;
    } else {
      if (round == 3 && CPU.get(0).getClass().equals(medium.class)) {
        CPU.get(0).setStrategy(new avgstrategy(prevfingers));
      } else if (round == 3 && (CPU.get(0).getClass().equals(hard.class))) {
        CPU.get(0).setStrategy(new topstrategy(prevfingers));
      } else if ((CPU.get(0).getClass().equals(master.class) && round >= 3)) {
        if (round % 2 == 0) {
          CPU.get(0).setStrategy(new topstrategy(prevfingers));
        } else {
          CPU.get(0).setStrategy(new avgstrategy(prevfingers));
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

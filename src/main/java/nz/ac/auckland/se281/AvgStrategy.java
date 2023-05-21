package nz.ac.auckland.se281;

import java.util.List;

public class AvgStrategy implements Strategy {

  private List<Integer> previousfingers;
  private int result;

  public AvgStrategy(List<Integer> previousfingers) {
    this.previousfingers = previousfingers;
  }

  @Override
  public int getFingers() {
    result = Utils.getRandomNumber(1, 5);
    return result;
  }

  @Override
  public int getSum() {
    double total = 0;
    for (int i = 0; i < previousfingers.size(); i++) {
      total = total + previousfingers.get(i);
    }
    int avg = (int) Math.round(total / previousfingers.size());

    return result + avg;
  }
}

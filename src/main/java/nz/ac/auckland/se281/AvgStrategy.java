package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class AvgStrategy implements Strategy {

  private List<Integer> prevfingers = new ArrayList<Integer>();
  private int result;

  public AvgStrategy(List<Integer> prevfingers) {
    this.prevfingers = prevfingers;
  }

  @Override
  public int getFingers() {
    result = Utils.getRandomNumber(1, 5);
    return result;
  }

  @Override
  public int getSum() {
    double total = 0;
    for (int i = 0; i < prevfingers.size(); i++) {
      total = total + prevfingers.get(i);
    }
    int avg = (int) Math.round(total / prevfingers.size());

    return result + avg;
  }
}

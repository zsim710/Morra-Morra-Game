package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class avgstrategy implements Strategy {

  private List<Integer> prevfingers = new ArrayList<Integer>();
  int result;

  public avgstrategy(List<Integer> prevfingers) {
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

    // System.err.println("----");
    // System.err.println(avg);
    // System.err.println("-----");

    return result + avg;
  }
}

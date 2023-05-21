package nz.ac.auckland.se281;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopStrategy implements Strategy {

  private List<Integer> prevfingers = new ArrayList<Integer>();
  private int result;

  public TopStrategy(List<Integer> prevfingers) {
    this.prevfingers = prevfingers;
  }

  @Override
  public int getFingers() {
    result = Utils.getRandomNumber(1, 5);
    return result;
  }

  @Override
  public int getSum() {
    Collections.sort(prevfingers);

    int maxcount = 0;
    int count = 1;
    int currentnum = prevfingers.get(0);
    for (int i = 1; i < prevfingers.size(); i++) {
      if (prevfingers.get(i) == prevfingers.get(i - 1)) {
        count++;
      } else {
        count = 1;
      }

      if (count >= maxcount) {
        currentnum = prevfingers.get(i - 1);
        maxcount = count;
      }
    }
    return currentnum + result;
  }
}

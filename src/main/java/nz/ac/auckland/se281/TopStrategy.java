package nz.ac.auckland.se281;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopStrategy implements Strategy {

  private List<Integer> previousfingers = new ArrayList<Integer>();
  private int result;

  public TopStrategy(List<Integer> previousfingers) {
    this.previousfingers = previousfingers;
  }

  @Override
  public int getFingers() {
    result = Utils.getRandomNumber(1, 5);
    return result;
  }

  @Override
  public int getSum() {

    // code sorts the list of previous fingers and then finds the most common
    Collections.sort(previousfingers);

    int maxcount = 0;
    int count = 1;
    // for loop will go through the list and find the most common number.
    int currentnum = previousfingers.get(0);
    for (int i = 1; i < previousfingers.size(); i++) {
      if (previousfingers.get(i) == previousfingers.get(i - 1)) {
        count++;
      } else {
        count = 1;
      }

      if (count >= maxcount) {
        currentnum = previousfingers.get(i - 1);
        maxcount = count;
      }
    }
    return currentnum + result;
  }
}

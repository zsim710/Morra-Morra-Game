package nz.ac.auckland.se281;

public class Random implements Strategy {

  int result;

  @Override
  public int getFingers() {
    result = Utils.getRandomNumber(1, 5);
    return result;
  }

  @Override
  public int getSum() {
    return Utils.getRandomNumber(result + 1, result + 5);
  }
}

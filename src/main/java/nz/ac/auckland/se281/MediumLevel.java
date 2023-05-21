package nz.ac.auckland.se281;

public class MediumLevel implements Level {

  private Strategy strategy;

  public MediumLevel(Strategy strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public int getFingers() {
    return strategy.getFingers();
  }

  @Override
  public int getSum() {
    return strategy.getSum();
  }
}

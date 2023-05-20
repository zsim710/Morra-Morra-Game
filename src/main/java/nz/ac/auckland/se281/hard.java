package nz.ac.auckland.se281;

public class hard implements level {

  private Strategy strategy;

  public hard(Strategy strategy) {
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

  @Override
  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}

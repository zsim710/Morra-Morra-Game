package nz.ac.auckland.se281;

public class easy implements level {

    private Strategy strategy;

    public easy(Strategy strategy) {
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
    }


    
}

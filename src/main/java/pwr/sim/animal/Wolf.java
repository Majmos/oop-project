package pwr.sim.animal;

public class Wolf extends Animal {
    @Override
    public char getAnimalChar() {
        return 'W';
    }

    @Override
    public int getStrength() {
        return 60;
    }
}
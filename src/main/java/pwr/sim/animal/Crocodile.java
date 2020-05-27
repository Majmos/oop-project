package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourCrocodile;

public class Crocodile extends Animal {
    public Crocodile(int x, int y) {
        super(new AiBehaviourCrocodile(), x, y);
    }

    @Override
    public char getAnimalChar() {
        return 'C';
    }
}

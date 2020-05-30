package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.animal.ai.AiBehaviourCrocodile;

public class Crocodile extends Animal {
    public Crocodile(Position2D position) {
        super(position);
    }

    @Override
    public char getAnimalChar() {
        return 'C';
    }
}

package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.animal.ai.AiBehaviourHippo;

public class Hippo extends Animal {

    public Hippo(Position2D position) {
        super(position);
    }

    @Override
    public char getAnimalChar() {
        return 'H';
    }
}

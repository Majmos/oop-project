package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.animal.ai.AiBehaviourAntelope;

public class Antelope extends Animal {
    public Antelope(Position2D position) {
        super(position);
    }

    @Override
    public char getAnimalChar() {
        return 'A';
    }
}

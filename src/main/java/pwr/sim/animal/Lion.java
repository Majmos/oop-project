package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.animal.ai.AiBehaviourLion;

public class Lion extends Animal {
    public Lion(Position2D position) {
         super(position);
    }

    @Override
    public char getAnimalChar() {
        return 'L';
    }
}

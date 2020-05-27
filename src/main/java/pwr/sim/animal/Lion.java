package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourLion;

public class Lion extends Animal {
    public Lion(int x, int y) {
        super(new AiBehaviourLion(), x, y);
    }

    @Override
    public char getAnimalChar() {
        return 'L';
    }
}

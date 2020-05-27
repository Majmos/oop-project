package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourHippo;

public class Hippo extends Animal {

    public Hippo(int x, int y) {
        super(new AiBehaviourHippo(), x, y);
    }

    @Override
    public char getAnimalChar() {
        return 'H';
    }
}

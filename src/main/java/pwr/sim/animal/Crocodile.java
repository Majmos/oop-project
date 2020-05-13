package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourCrocodile;

public class Crocodile extends Animal {
    public Crocodile(int y, int x) {
        super(new AiBehaviourCrocodile(), y, x);
    }
}

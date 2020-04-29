package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourCrocodile;

public class Crocodile extends Animal {
    public Crocodile() {
        super(new AiBehaviourCrocodile());
    }
}

package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourAntelope;

public class Antelope extends Animal {
    public Antelope() {
        super(new AiBehaviourAntelope());
    }
}

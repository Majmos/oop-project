package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourWolf;

public class Wolf extends Animal {
    public Wolf() {
        super(new AiBehaviourWolf());
    }

}

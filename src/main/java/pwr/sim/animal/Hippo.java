package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourHippo;

public class Hippo extends Animal {

    public Hippo() {
        super(new AiBehaviourHippo());
    }
}

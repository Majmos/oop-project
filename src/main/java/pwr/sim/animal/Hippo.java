package pwr.sim.animal;

import pwr.sim.animal.ai.AiBehaviourHippo;

public class Hippo extends Animal {

    public Hippo(int y, int x) {
        super(new AiBehaviourHippo(), y, x);
    }
}

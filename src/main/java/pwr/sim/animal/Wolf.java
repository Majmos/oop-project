package pwr.sim.animal;

import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviourWolf;

public class Wolf extends Animal {
    public Wolf(int x, int y, World world) {
        super(new AiBehaviourWolf(), x, y, world);
    }

    @Override
    public char getAnimalChar() {
        return 'W';
    }
}

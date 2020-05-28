package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;

public class Wolf extends Animal {
    public Wolf(Position2D position, World world) {
        super(new AiBehaviour(position), position, world);
    }

    @Override
    public char getAnimalChar() {
        return 'W';
    }
}

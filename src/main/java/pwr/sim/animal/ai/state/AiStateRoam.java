package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.animal.Animal;

public class AiStateRoam implements IAiState {
    public AiStateRoam(Animal animal) {
        this.position = animal.getPosition();
    }

    @Override
    public IAiState update() {
        if(numTicks >= 6) {
            numTicks = 0;
            return new AiStateSleep();
        }

        if(phase == 0) {
            position.move(1, 0);
        } else if(phase == 1) {
            position.move(0, 1);
        } else if(phase == 2) {
            position.move(-1, 0);
        } else if(phase == 3) {
            position.move(0, -1);
        }

        phase = (phase + 1)%4;
        numTicks++;
        return null;
    }

    private int phase = 0;
    private int numTicks = 0;
    private final Position2D position;
}

package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;

public class AiStateRoam implements IAiState {
    public AiStateRoam(Position2D position) {
        this.position = position;
    }

    @Override
    public IAiState update() {
        if(numTicks >= 6) {
            numTicks = 0;
            return new AiStateSleep();
        }

        if(phase == 0) {
            position.x += 1;
        } else if(phase == 1) {
            position.y += 1;
        } else if(phase == 2) {
            position.x -= 1;
        } else if(phase == 3) {
            position.y -= 1;
        }
        phase = (phase + 1)%4;
        numTicks++;
        return null;
    }

    private int phase = 0;

    private int numTicks = 0;

    private Position2D position;
}

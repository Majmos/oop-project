package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.animal.ai.AiBehaviour;

public class AiStateRoam implements IAiState {
    public AiStateRoam(AiBehaviour aiBehaviour) {
        this.aiBehaviour = aiBehaviour;
    }

    @Override
    public IAiState update() {
        if(numTicks >= 6) {
            numTicks = 0;
            return new AiStateSleep();
        }

        if(phase == 0) {
            aiBehaviour.animal.position.move(1, 0);
        } else if(phase == 1) {
            aiBehaviour.animal.position.move(0, 1);
        } else if(phase == 2) {
            aiBehaviour.animal.position.move(-1, 0);
        } else if(phase == 3) {
            aiBehaviour.animal.position.move(0, -1);
        }

        phase = (phase + 1)%4;
        numTicks++;
        return null;
    }

    private int phase = 0;
    private int numTicks = 0;
    private AiBehaviour aiBehaviour;
}

package pwr.sim.animal.ai;

import pwr.sim.animal.Position2D;
import pwr.sim.animal.ai.state.IAiState;

public abstract class AiBehaviour {
    public void update() {
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
    }

    protected IAiState[] currentState;
    private int phase = 0;
    public Position2D position;
}

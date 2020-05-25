package pwr.sim.animal.ai;

import pwr.sim.animal.ai.state.IAiState;

import java.util.List;
import java.util.Stack;

public abstract class AiBehaviour {
    public void update() {
        if(phase == 0) {
            x += 1;
        } else if(phase == 1) {
            y += 1;
        } else if(phase == 2) {
            x -= 1;
        } else if(phase == 3) {
            y -= 1;
        }
        phase = (phase + 1)%4;
    }

    protected IAiState[] currentState;
    private int phase = 0;
}

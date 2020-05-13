package pwr.sim.animal.ai;

import pwr.sim.animal.ai.state.IAiState;

import java.util.List;
import java.util.Stack;

public abstract class AiBehaviour {
    public abstract void update();

    protected IAiState[] currentState;
}

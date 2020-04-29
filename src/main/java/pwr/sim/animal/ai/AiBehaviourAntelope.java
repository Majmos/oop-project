package pwr.sim.animal.ai;

import pwr.sim.animal.ai.state.AiStateCopulate;
import pwr.sim.animal.ai.state.AiStateEatPlant;
import pwr.sim.animal.ai.state.AiStateLookForFood;
import pwr.sim.animal.ai.state.AiStateSleep;

public class AiBehaviourAntelope implements IAiBehaviour {
    @Override
    public void update() {

    }

    private AiStateLookForFood stateLookForFood;
    private AiStateSleep stateSleep;
    private AiStateEatPlant stateEatPlant;
    private AiStateCopulate stateCopulate;
}

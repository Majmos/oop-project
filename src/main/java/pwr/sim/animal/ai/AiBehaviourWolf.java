package pwr.sim.animal.ai;

import pwr.sim.animal.ai.state.AiStateCopulate;
import pwr.sim.animal.ai.state.AiStateEatCorpse;
import pwr.sim.animal.ai.state.AiStateHunt;
import pwr.sim.animal.ai.state.AiStateSleep;

public class AiBehaviourWolf implements IAiBehaviour {
    @Override
    public void update() {
        System.out.println("Bl00dW00lF");
    }

    private AiStateHunt stateHunt;
    private AiStateSleep stateSleep;
    private AiStateEatCorpse stateEatCorpse;
    private AiStateCopulate stateCopulate;
}

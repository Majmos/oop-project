package pwr.sim.animal.ai.state;

public class AiStateSleep implements IAiState {
    @Override
    public IAiState update() {
        if(numTicks >= 4) {
            numTicks = 0;
            return new AiStatePop();
        }
        numTicks++;
        return null;
    }

    private int numTicks = 0;
}

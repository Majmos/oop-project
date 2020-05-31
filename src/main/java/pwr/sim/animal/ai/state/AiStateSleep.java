package pwr.sim.animal.ai.state;

public class AiStateSleep implements IAiState {
    @Override
    public IAiState update() {
        numTicks++;
        if(numTicks >= 4) {
            numTicks = 0;
            return new AiStatePop();
        }
        return null;
    }

    private int numTicks = 0;
}

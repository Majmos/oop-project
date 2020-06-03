package pwr.sim.animal.ai.state;

public interface IAiState {
    IAiState update();

    default String debugInfo() {
        return String.format("%s { }", this.getClass().getSimpleName());
    }
}

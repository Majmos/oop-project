package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.*;

public class AiStateEvade implements IAiState {
    public AiStateEvade(Animal animal, Animal predator) {
        this.animal = animal;
        this.predator = predator;
    }

    @Override
    public IAiState update() {
        animal.evade(predator.getPosition());
        return null;
    }

    private final Animal animal;
    private final Animal predator;
}

package pwr.sim.animal.ai.state;

import pwr.sim.World;
import pwr.sim.animal.*;

/**
 * Stan ucieczki przed drapieżnikami.
 */
public class AiStateEvade implements IAiState {
    public AiStateEvade(Animal animal, Animal predator) {
        this.animal = animal;
        this.predator = predator;
    }

    /**
     * Metoda odpowiada za oddalanie się od najbliższego drapieżnika. Zwirze ucieka dopóki nie znajdzie
     * się w bezpiecznym dystansie od drapieżnika.
     */
    @Override
    public IAiState update() {
        animal.evade(predator.getPosition());
        return null;
    }

    private final Animal animal;
    private final Animal predator;
}

package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.tile.Tile;

public class AiStateEatCorpse implements IAiState {
    public AiStateEatCorpse(Animal animal) {
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        Tile tile = animal.getWorld().getTile(animal.getPosition());
        if(tile.getFlesh() <= 0 || animal.getHunger() >= 100) {
            return new AiStateHunt(animal);
        }
        tile.changeFlesh(-5);
        animal.changeHunger(40);
        if(animal.wantToMate) {
            return new AiStateCopulatePredator(animal);
        } else if(animal.isTired) {
            return new AiStateSleepPredator(animal);
        }
        return null;
    }

    private final Animal animal;
}

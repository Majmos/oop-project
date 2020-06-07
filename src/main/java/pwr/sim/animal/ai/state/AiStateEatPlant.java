package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.tile.Tile;

public class AiStateEatPlant implements IAiState {
    public AiStateEatPlant(Animal animal) {
        this.animal = animal;
    }
    @Override
    public IAiState update() {
        Tile tile = animal.getWorld().getTile(animal.getPosition());
        if(tile.getFlora() <= 0 || animal.getHunger() >= 100) {
            return new AiStateLookForFood(animal);
        }
        tile.changeFlora(-10);
        animal.changeHunger(20);
        if(animal.wantToMate) {
            return new AiStateCopulate(animal);
        } else if(animal.isTired) {
            return new AiStateSleep(animal);
        }
        return null;
    }

    private final Animal animal;
}

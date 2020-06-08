package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.tile.Tile;

/**
 * Stan jedzenia roślin.
 */
public class AiStateEatPlant implements IAiState {
    public AiStateEatPlant(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda odpowiada za zmniejszenie roślinności na komórce oraz zwiększenie najedzenia się zwierzęcia.
     * Gdy na komórce nie ma więcej roślinności to zwierze szuka innej komórki.
     */
    @Override
    public IAiState update() {
        Tile tile = animal.getWorld().getTile(animal.getPosition());
        if(tile.getFlora() <= 0 || animal.getHunger() >= 100) {
            return new AiStateLookForFood(animal);
        }
        tile.changeFlora(-10);
        animal.changeHunger(20);
        return null;
    }

    private final Animal animal;
}

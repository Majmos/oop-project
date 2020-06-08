package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.tile.Tile;

/**
 * Stan jedzenia martwej zdobyczy.
 */
public class AiStateEatCorpse implements IAiState {
    public AiStateEatCorpse(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda odpowiada za zwiększenie się najedzenia zwierzęcia i zmniejszenie ilości mięsa na komórce.
     * Gdy jedzenie na komórce się skończy to zwierze zacznie polować na następną zwierzyne.
     */
    @Override
    public IAiState update() {
        Tile tile = animal.getWorld().getTile(animal.getPosition());
        tile.changeFlesh(-5);
        animal.changeHunger(40);
        if(tile.getFlesh() <= 0 || animal.getHunger() >= 100) {
            return new AiStateHunt(animal);
        }
        return null;
    }

    private final Animal animal;
}

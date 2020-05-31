package pwr.sim.animal.ai.state;

import pwr.sim.animal.Animal;
import pwr.sim.tile.Tile;

public class AiStateEatPlant implements IAiState {
    public AiStateEatPlant(Animal animal) {
        this.tile = animal.getWorld().getTile(animal.getPosition());
        this.flora = tile.getFlora();
        this.animal = animal;
        this.hunger = animal.getHunger();
    }
    @Override
    public IAiState update() {
        if(flora >= 5 && hunger < 100) {
            tile.changeFlora(-5);
            animal.changeHunger(5);
            return null;
        } else {
            //what should be returned when animal is not hungry or there is no more flora in this tile
        }
        return null;
    }

    private int flora;
    private int hunger;
    private Tile tile;
    private Animal animal;
}

package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.tile.ForestTile;
import pwr.sim.tile.Tile;

/**
 * Stan w którym zwierze szuka jedzenia.
 */
public class AiStateLookForFood implements IAiState {
    public AiStateLookForFood(Animal animal) {
        this.animal = animal;
    }

    /**
     * Metoda odpowiadająca za znalezienie komórki z pożywieniem oraz kierowaniem się ku niemu.
     * Gdy zwierze dojdzie do komórki z pożywieniem to przechodzi w stan jedzenia i gdy jest juz najedzone to zmienia stan.
     */
    @Override
    public IAiState update() {
        Position2D position = animal.getPosition();
        World world = animal.getWorld();
        int minimum = 100000;
        if(world.getTile(position) instanceof ForestTile && world.getTile(position).getFlora() > 0) {
            return new AiStateEatPlant(animal);
        }
        if(destination == null) {
            destination = world.findNearestPlants(animal.getPosition());
        }
        if(destination != null) {
            animal.approach(destination);
            if (animal.getHunger() >= 100) {
                return new AiStateSleep(animal);
            }
            if (animal.getPosition().equals(destination)) {
                destination = null;
                return new AiStateEatPlant(animal);
            }
        }
        return null;
    }

    private final Animal animal;
    private Position2D destination;
}
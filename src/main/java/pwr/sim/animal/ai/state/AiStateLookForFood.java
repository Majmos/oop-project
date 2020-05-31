package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.tile.ForestTile;

public class AiStateLookForFood implements IAiState {
    public AiStateLookForFood(Animal animal) {
        this.position = animal.getPosition();
        this.world = animal.getWorld();
        this.animal = animal;
    }

    @Override
    public IAiState update() {
        if(animal.getHunger() == 100) {
            return new AiStatePop();
        }
        if(world.getTile(position) instanceof ForestTile) {
            return new AiStateEatPlant(animal);
        }
        if(!hasDestination) {
            for (int y = position.getY() - 5; y < position.getY() + 5; y++) {
                for (int x = position.getX() - 5; x < position.getX() + 5; x++) {
                    if (world.getTile(x, y) instanceof ForestTile) {
                        distanceX = x - position.getX();
                        distanceY = y - position.getY();
                        if (Math.abs(distanceX) + Math.abs(distanceY) < minimum) {
                            minimum = Math.abs(distanceX) + Math.abs(distanceY);
                            minX = distanceX;
                            minY = distanceY;
                            hasDestination = true;
                        }
                    }
                }
            }
        }
        if(minX < 0) {
            position.move(-1,0);
            minX++;
        } else if(minX > 0) {
            position.move(1,0);
            minX--;
        }
        if(minY < 0) {
            position.move(0,-1);
            minY++;
        } else if(minY > 0) {
            position.move(0,1);
            minY--;
        }
        if(minX == 0 && minY == 0) hasDestination = false;
        return null;
    }

    private World world;
    private Position2D position;
    private Animal animal;
    private int distanceX = 0;
    private int distanceY = 0;
    private int minimum = 100000;
    private int minX = 0;
    private int minY = 0;
    private boolean hasDestination = false;
}
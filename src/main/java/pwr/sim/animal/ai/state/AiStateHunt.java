package pwr.sim.animal.ai.state;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.Animal;
import pwr.sim.animal.Antelope;
import pwr.sim.animal.Hippo;

import java.util.List;

public class AiStateHunt implements IAiState {
    public AiStateHunt(Animal animal) {
        this.position = animal.getPosition();
        World world = animal.getWorld();
        this.animal = animal;
        this.animals = world.getAnimals();
    }

    @Override
    public IAiState update() {
        if (animal.getHunger() == 100) {
            return new AiStatePop();
        }
        if(prey == null) {
            for (Animal prey: animals) {
                if (prey instanceof Antelope || prey instanceof Hippo) {
                    Position2D preyPosition = prey.getPosition();
                    int distanceX = preyPosition.getX() - position.getX();
                    int distanceY = preyPosition.getY() - position.getY();
                    int currentDistance = Math.abs(distanceX) + Math.abs(distanceY);
                    if (currentDistance < minimum) {
                        minimum = currentDistance;
                        minX = distanceX;
                        minY = distanceY;
                        this.prey = prey;
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
        if(minX == 0 && minY == 0) {
            assert prey != null;
            prey.changeHealth(-100);
            prey = null;
            return new AiStateEatCorpse(animal);
        }
        return null;
    }

    private final List<Animal> animals;
    private final Animal animal;
    private final Position2D position;
    private int minimum = 100000;
    private int minX = 0;
    private int minY = 0;
    private Animal prey = null;
}

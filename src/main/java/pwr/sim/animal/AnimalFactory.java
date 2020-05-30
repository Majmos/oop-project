package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;

public class AnimalFactory {
    public AnimalFactory(World world) {
        this.world = world;
    }

    public Animal createAnimal(AnimalType animalType, Position2D position) {
        Animal animal;
        switch (animalType) {
            case ANTELOPE:
                animal = new Antelope(position);
                break;
            case CROCODILE:
                animal = new Crocodile(position);
                break;
            case HIPPO:
                animal = new Hippo(position);
                break;
            case LION:
                animal = new Lion(position);
                break;
            case WOLF:
                animal = new Wolf(position, world);
                break;
            default:
                return null;
        }
        animal.setAiBehaviour(new AiBehaviour(animal));
        return animal;
    }

    private World world;
}


package pwr.sim.animal;

import pwr.sim.Position2D;
import pwr.sim.World;
import pwr.sim.animal.ai.AiBehaviour;
import pwr.sim.animal.ai.AiBehaviourHerbivore;
import pwr.sim.animal.ai.AiBehaviourPredator;

public class AnimalFactory {
    public AnimalFactory(World world) {
        this.world = world;
    }

    public Animal createAnimal(AnimalType animalType, Position2D position) {
        Animal animal;
        AiBehaviour aiBehaviour;
        switch (animalType) {
            case ANTELOPE:
                animal = new Antelope();
                aiBehaviour = new AiBehaviourHerbivore(animal);
                break;
            case CROCODILE:
                animal = new Crocodile();
                aiBehaviour = new AiBehaviourPredator(animal);
                break;
            case HIPPO:
                animal = new Hippo();
                aiBehaviour = new AiBehaviourHerbivore(animal);
                break;
            case LION:
                animal = new Lion();
                aiBehaviour = new AiBehaviourPredator(animal);
                break;
            case WOLF:
                animal = new Wolf();
                aiBehaviour = new AiBehaviourPredator(animal);
                break;
            default:
                return null;
        }
        animal.setWorld(this.world);
        try {
            animal.setPosition(position);
        } catch (Exception e) {
            return null;
        }
        animal.setAiBehaviour(aiBehaviour);

        return animal;
    }

    public Animal createAnimal(Animal animal, Position2D position) {
        try {
            Animal other = animal.getClass().getConstructor().newInstance();
            AiBehaviour aiBehaviour = animal.getAiBehaviour().getClass().getConstructor().newInstance();
            other.setWorld(this.world);
            try {
                other.setPosition(position);
            } catch (Exception e) {
                return null;
            }
            other.setAiBehaviour(aiBehaviour);
            return other;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    private final World world;
}
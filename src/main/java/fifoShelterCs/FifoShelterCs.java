package fifoShelterCs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FifoShelterCs
{
  private class AnimalWrapper
  {
    private Animal animal;
    private int number;

    public AnimalWrapper(Animal animal, int number)
    {
      this.animal = animal;
      this.number = number;
    }
  }

  private int queueCounter = 0;
  private Map<Class<? extends Animal>, LinkedList<AnimalWrapper>> animalMap = new HashMap<Class<? extends Animal>, LinkedList<AnimalWrapper>>();

  public void enqueue(Animal animal)
  {
    LinkedList<AnimalWrapper> animalQueue = animalMap.get(animal.getClass());
    if (animalQueue == null)
    {
      animalQueue = new LinkedList<FifoShelterCs.AnimalWrapper>();
      animalMap.put(animal.getClass(), animalQueue);
    }
    animalQueue.add(new AnimalWrapper(animal, queueCounter++));
  }

  public Animal dequeueAny()
  {
    AnimalWrapper oldestAnimal = null;
    for (LinkedList<AnimalWrapper> animalQueue : animalMap.values())
    {
      AnimalWrapper wrapper = animalQueue.peek();
      if (oldestAnimal != null && wrapper != null)
      {
        if (oldestAnimal.number > wrapper.number)
        {
          oldestAnimal = wrapper;
        }
      }
      else if (wrapper != null)
      {
        oldestAnimal = wrapper;
      }
    }

    if (oldestAnimal != null)
    {
      return animalMap.get(oldestAnimal.animal.getClass()).pollFirst().animal;
    }
    return null;
  }

  public Animal dequeueDog()
  {
    return dequeueByType(Dog.class);
  }

  public Animal dequeueCat()
  {
    return dequeueByType(Cat.class);
  }

  public Animal dequeueByType(Class<? extends Animal> type)
  {
    LinkedList<AnimalWrapper> animalQueue = animalMap.get(type);
    if (animalQueue != null)
    {
      AnimalWrapper wrapper = animalQueue.pollFirst();
      if (wrapper != null)
      {
        return wrapper.animal;
      }
    }
    return null;
  }

  public static void main(String[] args)
  {
    FifoShelterCs shelter = new FifoShelterCs();
    Animal[] animals = new Animal[] { new Dog("Fido"), new Iguana("Lizzy"), new Dog("Butch"), new Cat("Fluffy"), new Dog("Walter"),
                                     new Cat("Snowball") };
    for (int i = 0; i < animals.length; ++i)
    {
      shelter.enqueue(animals[i]);
    }
    Animal animal = shelter.dequeueAny();
    while (animal != null)
    {
      System.out.println(animal.getClass() + ": " + animal.getName());
      animal = shelter.dequeueAny();
    }
  }
}

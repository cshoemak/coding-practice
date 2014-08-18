package testpkg;

import java.util.LinkedList;

public class FifoShelterCs
{
  private class Animal
  {
    private int number;
    private String name;

    public Animal(int n, String name)
    {
      this.number = n;
      this.name = name;
    }

    public int getNumber()
    {
      return number;
    }

    public String getName()
    {
      return name;
    }
  }
  
  private enum AnimalType
  {
    DOG, CAT;
  }

  private LinkedList<Animal> dogs = new LinkedList<Animal>();
  private LinkedList<Animal> cats = new LinkedList<Animal>();
  private int queueCounter = 0;
  
  public void enqueue(AnimalType type, String name)
  {
    Animal animal = new Animal(queueCounter++, name);
    switch (type)
    {
      case DOG:
        dogs.add(animal);
        break;
      case CAT:
        cats.add(animal);
        break;
      default:
        throw new IllegalArgumentException("Unsupported animal type. We only take cats and dogs.");
    }
  }

  public String dequeueAny()
  {
    Animal oldestDog = dogs.peek();
    Animal oldestCat = cats.peek();
    Animal returnVal = null;
    
    if (oldestDog != null && oldestCat != null)
    {
      if (oldestDog.getNumber() < oldestCat.getNumber())
      {
        returnVal = dogs.pollFirst();
      }
      else
      {
        returnVal = cats.pollFirst();
      }
    }
    else if (oldestDog == null)
    {
      returnVal = cats.pollFirst();
    }
    else
    {
      returnVal = dogs.pollFirst();
    }

    return getStringOrNullFromAnimal(returnVal);
  }

  private String getStringOrNullFromAnimal(Animal animal)
  {
    String returnStr = null;
    if (animal != null)
    {
      returnStr = animal.getName();
    }
    return returnStr;
  }
  
  public String dequeueDog()
  {
    return getStringOrNullFromAnimal(dogs.pollFirst());
  }

  public String dequeueCat()
  {
    return getStringOrNullFromAnimal(cats.pollFirst());
  }

  public static void main(String[] args)
  {
    FifoShelterCs shelter = new FifoShelterCs();
    String[] animals = new String[] { "Fido", "Butch", "Fluffy", "Walter", "Snowball" };
    AnimalType[] types = new AnimalType[] { AnimalType.DOG, AnimalType.DOG, AnimalType.CAT, AnimalType.DOG, AnimalType.CAT };
    for (int i = 0; i < animals.length; ++i)
    {
      shelter.enqueue(types[i], animals[i]);
    }
    String animal = shelter.dequeueDog();
    while (animal != null)
    {
      System.out.println(animal);
      animal = shelter.dequeueDog();
    }
  }
}

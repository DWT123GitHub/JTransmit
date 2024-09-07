package jtransmission.location;

import jtransmission.person.*;
import java.util.Collection;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


public class Location_impl implements Location
{
    // A Collection of people at this location
    private Collection<Person> people;

    // A collection of destinations that can be travelled to from this location
    private Collection<Location> destinations;

    // The name of this location
    private String name;

    private static Random rand = new Random();

    private int x;
    private int y;

    // A constructor for this location
    public Location_impl(String name, int x, int y)
    {
        this.name = name;
        this.destinations = new ArrayList<Location>();
        this.people = new ArrayList<Person>();
        this.x = x;
        this.y = y;

    }

    public void printLocation()
    {
        System.out.println("Location: " + name);
        for (Person person : people)
        {
            //print persons name and if they are infected
            System.out.println(person.getName() + " " + person.getStatus());
        }
        System.out.println();
    }

    // A method to add a person to this location
    public void addPerson(Person person)
    {
        people.add(person);
    }

    // A method to remove a person from this location
    public void removePerson(Person person)
    {
        people.remove(person);
    }

    // A method to add a destination to this location
    public void addDestination(Location destination)
    {
        destinations.add(destination);
    }

    // A method to get people at location
    public Collection<Person> getPeople()
    {
        return people;
    }

    // A function to get the destinations from a location
    public Collection<Location> getDestinations()
    {
        return destinations;
    }

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void addPeople(Collection<Person> people)
    {
        for (Person person : people)
        {
            addPerson(person);
        }
    }

    // A function to move people to another location
    public void movePeople()
    {
        Collection<Person> peopleToRemove = new ArrayList<Person>();
        Map<Location, Collection<Person>> peopleToAdd = new HashMap<Location, Collection<Person>>();


        for(Iterator<Person> it = people.iterator(); it.hasNext(); ) 
        {
            Person p = it.next();

            peopleToRemove.add(p);

            // Get a random destination
            int index = rand.nextInt(destinations.size());
            Location destination = (Location)destinations.toArray()[index];

            if(!peopleToAdd.containsKey(destination))
                peopleToAdd.put(destination, new ArrayList<Person>());

            peopleToAdd.get(destination).add(p);        
        }
        people.removeAll(peopleToRemove);

        for (Map.Entry<Location, Collection<Person>> entry : peopleToAdd.entrySet())
        {
            Location destination = entry.getKey();
            Collection<Person> people = entry.getValue();

            destination.addPeople(people);
        }
    }

    public void infectPeople()
    {
        for (Person person : people)
        {
            if (person.getStatus() == Person.Status.Infected)
            {
                for (Person otherPerson : people)
                {
                    if (otherPerson.getStatus() == Person.Status.Healthy)
                    {
                        if (rand.nextInt(100) < 33)
                        {
                            otherPerson.setStatus(Person.Status.Infected);
                            otherPerson.setInfectionPeriod(10);
                        }
                    }
                }
                person.heal();
            }
        }
    }

}

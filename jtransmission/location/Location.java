package jtransmission.location;

import java.util.Collection;

import jtransmission.person.*;

public interface Location 
{    

    // The signatures of the functions that must be implemented by a location
    public void addPerson(Person person);
    public void addPeople(Collection<Person> people);    
    public void removePerson(Person person);
    public void addDestination(Location destination);

    // Print the location
    public void printLocation();

    // A method to get people at location
    public Collection<Person> getPeople();

    // A function to get the destinations from a location
    public Collection<Location> getDestinations();

    // A function to move people to another location
    public void movePeople();

    public String getName();

    public int getX();

    public int getY();

    public void infectPeople();
}

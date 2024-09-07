package jtransmission;

import jtransmission.display.Display_impl;
import jtransmission.location.*;
import jtransmission.person.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;
import jtransmission.person.Person.Status;

import javax.swing.SwingUtilities;


public class JTransmission 
{        
    private static Random rand = new Random();

    public static void main(String[] args) 
    {
        Collection<Location> locations = new ArrayList<Location>();

        createLocations(locations);
        createPeople(locations);

        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                Display_impl display = new Display_impl(locations);
                display.setVisible(true);  
            }
        });
    }


    /* A function to create a set of locations:

        */
    public static void createLocations(Collection<Location> locations)
    {
        Location chippenham = new Location_impl("Chippenham", 225, 400);
        Location bristol = new Location_impl("Bristol", 420, 405);
        Location london = new Location_impl("London", 275, 410);
        Location york = new Location_impl("York", 250, 300);
        Location manchester = new Location_impl("Manchester", 200, 325);
        Location edinburgh = new Location_impl("Edinburgh", 175, 210);
        Location southampton = new Location_impl("Southampton", 225, 433);
        Location cambridge = new Location_impl("Cambridge", 275, 365);

        locations.add(chippenham);
        chippenham.addDestination(bristol);
        chippenham.addDestination(london);
        chippenham.addDestination(southampton);

        locations.add(bristol);
        bristol.addDestination(chippenham);
        bristol.addDestination(manchester);

        locations.add(london);
        london.addDestination(chippenham);
        london.addDestination(york);
        london.addDestination(southampton);
        london.addDestination(cambridge);

        locations.add(york);
        york.addDestination(london);
        york.addDestination(edinburgh);
        york.addDestination(cambridge);

        locations.add(manchester);
        manchester.addDestination(bristol);
        manchester.addDestination(edinburgh);

        locations.add(edinburgh);
        manchester.addDestination(manchester);
        edinburgh.addDestination(york);

        locations.add(southampton);
        southampton.addDestination(london);
        southampton.addDestination(chippenham);

        locations.add(cambridge);
        cambridge.addDestination(london);
        cambridge.addDestination(york);

    }

    /* A function to create the following people and randomly assign them to a location:
     * - Dan
     * - Sophie
     * - Gem
     * - Will
     */
    public static void createPeople(Collection<Location> locations)
    {
        Collection<Person> people = new ArrayList<Person>();

        Person dan = new Person_impl("Dan");
        Person sophie = new Person_impl("Sophie");
        Person gem = new Person_impl("Gem");
        Person will = new Person_impl("Will");
        Person megan = new Person_impl("Megan");
        Person caitlin = new Person_impl("Caitlin");
        Person owen = new Person_impl("Owen");
        Person hanna = new Person_impl("Hanna");
        Person joe = new Person_impl("Joe");
        Person al = new Person_impl("Al");
        Person josh = new Person_impl("Josh");

        people.add(dan);
        people.add(sophie);
        people.add(gem);
        people.add(megan);
        people.add(caitlin);
        people.add(hanna);
        people.add(joe);
        people.add(owen);
        people.add(al);
        people.add(josh);        
        people.add(will);        

        dan.setStatus(Status.Infected);
        dan.setInfectionPeriod(10);

        for (Person person : people)
        {
            int randomLocation = rand.nextInt( locations.size() );
            Location location = (Location)locations.toArray()[randomLocation];
            location.addPerson(person);
        }
    }
}
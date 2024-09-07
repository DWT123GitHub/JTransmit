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
        Location home = new Location_impl("Home", 250, 475);
        Location rugbyClub = new Location_impl("Rugby Club", 150, 325);
        Location school = new Location_impl("School", 400, 225);
        Location sainsburys = new Location_impl("Sainsburys", 200, 550);
        Location lidl = new Location_impl("Lidl", 300, 300);
        Location CGI = new Location_impl("CGI", 300, 600);
        Location station = new Location_impl("Station", 700, 350);
        Location town = new Location_impl("Town", 650, 400);

        locations.add(home);
        home.addDestination(school);
        home.addDestination(lidl);
        home.addDestination(town); 
        home.addDestination(station);
        home.addDestination(sainsburys);
        home.addDestination(CGI);
        home.addDestination(rugbyClub);

        locations.add(rugbyClub);
        rugbyClub.addDestination(home);
        rugbyClub.addDestination(school);

        locations.add(school);
        school.addDestination(home);
        school.addDestination(lidl);
        school.addDestination(town);

        locations.add(sainsburys);
        sainsburys.addDestination(CGI);
        sainsburys.addDestination(home);

        locations.add(lidl);
        lidl.addDestination(school);
        lidl.addDestination(home);



        locations.add(CGI);
        CGI.addDestination(home);
        CGI.addDestination(sainsburys);

        locations.add(station);
        station.addDestination(town);
        station.addDestination(home);

        locations.add(town);
        town.addDestination(home);
        town.addDestination(school);
        town.addDestination(station);
        town.addDestination(lidl);
        town.addDestination(rugbyClub);
        town.addDestination(CGI);
        town.addDestination(sainsburys);

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
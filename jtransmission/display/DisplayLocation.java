package jtransmission.display;

import java.util.Collection;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import jtransmission.location.Location;
import jtransmission.person.Person;
import jtransmission.person.Person.Status;
import java.util.Map;
import java.util.HashMap;


public class DisplayLocation 
{
    Location location;
    JLabel locationLabel = new JLabel();     
    Collection<JLabel> locationLabels = new ArrayList<JLabel>();     

    Map<Status, Color> healthLookup = new HashMap<Status, Color>() 
    {
        {
            put(Status.Healthy, Color.BLACK);
            put(Status.Infected, Color.RED);
            put(Status.Immune, Color.GREEN);
            put(Status.Dead, Color.GRAY);
        }
    };

    //A constructor for the DisplayLocation class
    public DisplayLocation(Location location)
    {
        super();

        this.location = location;

        locationLabel.setText(location.getName());
        locationLabel.setSize(locationLabel.getPreferredSize());
        locationLabel.setLocation(location.getX(),location.getY());
        locationLabels.add(locationLabel);

        int i = 1;
        for (Person person : location.getPeople())        
        {
            JLabel personName = new JLabel(person.getName());
            personName.setSize(personName.getPreferredSize());
            personName.setLocation(location.getX() + 10,location.getY() + (i++ * 10));
            locationLabels.add(personName);
        }

        for(; i < 6; i++)
        {
            JLabel empty = new JLabel("");
            empty.setSize(empty.getPreferredSize());
            empty.setLocation(location.getX() + 10,location.getY() + (i * 10));
            locationLabels.add(empty);
        }
       
    }

    Collection<JLabel> getPeopleLabels()
    {
        return locationLabels;
    }

    JLabel getLocationLabel()
    {
        return locationLabel;
    }

    void clearPeopleLabels()
    {
        for (JLabel label : locationLabels)
        {
            if (label.getText() != "")
            {
                label.setText("");
            }
        }
    }    

    void populateLocations()
    {
        locationLabel.setText(location.getName());
        locationLabel.setSize(locationLabel.getPreferredSize());
        locationLabel.setLocation(location.getX(),location.getY());
        locationLabels.add(locationLabel);

        int i = 1;
        for (Person person : location.getPeople())        
        {
            JLabel personName = new JLabel(person.getName());
            personName.setSize(personName.getPreferredSize());
            personName.setLocation(location.getX() + 10,location.getY() + (i++ * 10));        
            personName.setForeground(healthLookup.get(person.getStatus()));    
            locationLabels.add(personName);
        }

        for(; i < 6; i++)
        {
            JLabel empty = new JLabel("");
            empty.setSize(empty.getPreferredSize());
            empty.setLocation(location.getX() + 10,location.getY() + (i * 10));
            locationLabels.add(empty);
        }
    }
}

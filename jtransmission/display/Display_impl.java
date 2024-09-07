package jtransmission.display;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jtransmission.location.Location;
import java.util.Collection;
import java.util.ArrayList;


/* A class for craating a Swing interface*/
public class Display_impl extends JFrame implements Display
{
    private Integer day = 1;
    ActionListener listerner;
    Collection<Location> locations;
    Collection<DisplayLocation>displayLocations = new ArrayList<DisplayLocation>();
    JButton button = new JButton("Click me");
    JLabel daysLabel  = new JLabel(day.toString());

    public Display_impl(Collection<Location> locations)
    {
        this.locations = locations;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel map = new JPanel();
        add(map, BorderLayout.CENTER);

        ImageIcon image = new ImageIcon("very_small_map.jpg");
        JLabel label = new JLabel(image);
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setSize(new Dimension(1000, 1000));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        map.add(scrollPane, BorderLayout.CENTER);  
        
        //for each location create a display location
        for (Location location : locations)
        {
            DisplayLocation dl = new DisplayLocation(location);
            displayLocations.add(dl);

            label.add(dl.getLocationLabel());
            for (JLabel l : dl.getPeopleLabels())
            {
                label.add(l);
            }
        }
    
        JPanel control = new JPanel();
        add(control, BorderLayout.EAST);

        listerner = new ActionListener()  
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                updateModel();   
                
                for (DisplayLocation dl : displayLocations)
                {                
                    //clear the labels at this location
                    for (JLabel label : dl.getPeopleLabels())
                    {
                        label.setText("");
                    }

                    dl.populateLocations();

                    label.add(dl.getLocationLabel());
                    for (JLabel l : dl.getPeopleLabels())
                    {
                        label.add(l);
                        label.repaint();
                    }
                }
                
            }  
        };

        button.addActionListener(listerner);

        control.add(button, BorderLayout.NORTH);
        control.add(daysLabel, BorderLayout.SOUTH);

        pack();
    }

    void updateModel()
    {
        day++;
        daysLabel.setText(day.toString());

        for (Location location : locations)
        {
            location.movePeople();            
        }

        for (Location location : locations)
        {
            location.infectPeople();
        }

        for (Location location : locations)
        {
            location.printLocation();
        }
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();
    }
}


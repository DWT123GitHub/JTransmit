package jtransmission.person;

import jtransmission.person.Person;



public class Person_impl implements Person
{
    // The name of the person
    private String name;
    private Status status;
    private int infectionPeriod;

    // A constructor
    public Person_impl(String name)
    {
        this.name = name;
        this.status = Status.Healthy;
    }

    public void heal()
    {
        if(status == Person.Status.Infected)
        {
            infectionPeriod--;
        }
        if(infectionPeriod == 0)
        {
            this.status = Status.Immune;
        }
    }

    public String getName()
    {
        return name;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public void setInfectionPeriod(int value)
    {
        this.infectionPeriod = value;
    }
}

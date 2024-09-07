package jtransmission.person;



public interface Person 
{
    //an Enum called Status that has states Healthy, Infected, Immune and Dead
    public enum Status
    {
        Healthy, Infected, Immune, Dead
    }

    // Get the name of the person
    public String getName();

    // get the status of the person
    public Status getStatus();

    //set the status of the person
    public void setStatus(Status status);

    public void setInfectionPeriod(int value);

    public void heal();
}

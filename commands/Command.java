package ParkingLot.commands;

public interface Command {
    public boolean matches(String input);
    public void execute();
}

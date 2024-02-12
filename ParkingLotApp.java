package ParkingLot;

import ParkingLot.commands.CommandRegistry;

import java.util.Scanner;

public class ParkingLotApp {
    public static void main(String[] args) {
        CommandRegistry commandRegistry = new CommandRegistry();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Do the task you want to do");
            String input = sc.next();
            commandRegistry.execute(input);
        }
    }
}

package main;

import logic.Computer;

public class Main {
    public static void main(String[] args) throws Exception {

        Computer computer = new Computer();

       // computer.writeDirectories();
        computer.readDirectories();

        for (int i = 0; i < computer.getDirectories().size(); i++)
            System.out.println(computer.getDirectories().get(i));

    }
}

package main;

import logic.Computer;
import logic.Director;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Computer computer = Computer.getInstance();

       //computer.writeDirectories();
       computer.readDirectories();

//        for (int i = 0; i < computer.getDirectories().size(); i++)
//            System.out.println(computer.getDirectories().get(i));


        Scanner scanner=new Scanner(System.in);
        String response;

        System.out.println("Welcome to the Media Files Management App!!");
        System.out.println("---------------------------------------------");



        while(true)
        {

            System.out.println("Type `continue` to view the directories on this computer or `exit` to close the app.");

            response= scanner.nextLine();
            if(response.equals("continue"))
            {
                for(Director d: computer.getDirectories())
                    System.out.println(d.getPath());
            }

            if(response.equals("exit"))
                System.exit(0);
        }
    }
}

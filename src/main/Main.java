package main;

import logic.Computer;

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

        while(true)
        {
            response= scanner.nextLine();
            System.out.println(response);

            if(response.equals("exit"))
                System.exit(0);
        }
    }
}

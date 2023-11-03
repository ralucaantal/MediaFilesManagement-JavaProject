package main;

import exceptions.MultimediaException;
import logic.Computer;
import logic.Director;
import logic.File;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Computer computer = Computer.getInstance();

        //computer.writeDirectories();
        computer.readDirectories();

//        for (int i = 0; i < computer.getDirectories().size(); i++)
//            System.out.println(computer.getDirectories().get(i));


        Scanner scanner = new Scanner(System.in);
        String response;

        String location = "Home";
        int directorNo = 0;


        System.out.println("Welcome to the Media Files Management App!!");
        System.out.println("---------------------------------------------");

        // System.out.println("Type `continue` to view the directories on this computer or `exit` to close the app.");

        while (true) {
            switch (location) {
                case "Home":
                    System.out.println("Type `continue` to view the directories on this computer or `exit` to close the app.");
                    response = scanner.nextLine();
                    try {
                        if (response.equals("continue")) {
                            System.out.println("Here are your directories: Choose one to navigate inside it: ");
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }


                            location = "Directories";
                        } else if (response.equals("exit")) {
                            computer.writeDirectories(computer.getDirectories());

                            System.exit(0);
                        } else {
                            throw new MultimediaException("Wrong answer! Try again.");
                        }
                    } catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Directories":
                    try {
                        System.out.println("Or choose an action: add | rename | delete");

                        response = scanner.nextLine();

                        if (response.equals("exit")) {
                            computer.writeDirectories(computer.getDirectories());

                            System.exit(0);
                        } else if (response.equals("back")) {
                            location = "Home";
                        }
                        // DE FACUT O PAGINA NOUA IN MENIU
                        else if(response.equals("add"))
                        {
                            System.out.println("Type the path of the new director: ");

                            response = scanner.nextLine();

                            Director newDirector=new Director(response);

                            computer.getDirectories().add(newDirector);
                        }

                       else  if (!computer.getDirectories().isEmpty()) {

                          if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                directorNo = resp;
                                System.out.println("-------------------------------------");
                                System.out.println("In directory " + computer.getDirectories().get(resp).getPath() + " you have the following files: ");


                                for (int i = 0; i < computer.getDirectories().get(resp).getFiles().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(resp).getFiles().get(i).getName());
                                }

                                System.out.println("-------------------------------------");
                                location = "Files";
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }

                        } else {
                            throw new MultimediaException("You have no directories in this computer!");
                        }
                        System.out.println();
                    } catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Files":
                    try {
                        System.out.println("If you want details for a specific file, type the index of the file. Write `back` to go back. Write `exit` to close the app. ");
                        response = scanner.nextLine();
                        if (response.equals("exit")) {
                            computer.writeDirectories(computer.getDirectories());

                            System.exit(0);
                        } else if (response.equals("back")) {
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }
                            location = "Directories";
                        } else if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().get(directorNo).getFiles().size()) {
                            System.out.println("Full details for your file: ");
                            int index = (int) (Double.parseDouble(response) - 1);
                            System.out.println(computer.getDirectories().get(directorNo).getFiles().get(index));

                            System.out.println("Write `back` to go back. Write `exit` to close the app. ");
                            response = scanner.nextLine();
                            if (response.equals("back")) {
                                for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                }
                                location = "Files";
                            } else if (response.equals("exit")) {
                                computer.writeDirectories(computer.getDirectories());

                                System.exit(0);
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }
                        } else {
                            throw new MultimediaException("Invalid index! Try again.");
                        }

                    } catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

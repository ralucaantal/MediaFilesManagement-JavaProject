package main;

import exceptions.MultimediaException;
import logic.Computer;
import logic.Director;
import logic.File;

import java.sql.SQLOutput;
import java.util.ArrayList;
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
                            System.out.println("Or choose an action: add | rename | delete");


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


                        response = scanner.nextLine();

                        if (response.equals("exit")) {
                            computer.writeDirectories(computer.getDirectories());

                            System.exit(0);
                        } else if (response.equals("back")) {
                            location = "Home";
                        } else if (response.equals("add")) {
                            System.out.println("Type the path of the new director: ");

                            response = scanner.nextLine();

                            Director newDirector = new Director(response);

                            computer.getDirectories().add(newDirector);
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }
                            System.out.println("Or choose an action: add | rename | delete");
                            location = "Directories";
                        } else if (response.equals("rename")) {
                            System.out.println("Type the index of the directory you want to rename: ");

                            response = scanner.nextLine();
                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                System.out.println("Actual name: " + computer.getDirectories().get(resp).getPath());
                                System.out.println("Type new name for the directory: ");
                                response = scanner.nextLine();
                                computer.getDirectories().get(resp).setPath(response);
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }
                            System.out.println("Or choose an action: add | rename | delete");
                            location = "Directories";

                        } else if (response.equals("delete")) {
                            System.out.println("Type the index of the directory you want to delete: ");

                            response = scanner.nextLine();
                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                computer.getDirectories().remove(resp);
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }
                            System.out.println("Or choose an action: add | rename | delete");
                            location = "Directories";
                        } else if (!computer.getDirectories().isEmpty()) {

                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                directorNo = resp;
                                System.out.println("-------------------------------------");
                                System.out.println("In directory " + computer.getDirectories().get(resp).getPath() + " you have the following files: ");

                                if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                    for (int i = 0; i < computer.getDirectories().get(resp).getFiles().size(); i++) {
                                        System.out.println(i + 1 + ". " + computer.getDirectories().get(resp).getFiles().get(i).getName());
                                    }
                                    System.out.println("-------------------------------------");

                                }

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
                        if (computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                            System.out.println("There are no files in this directory!");
                            System.out.println("-------------------------------------");

                            System.out.println("Choose an action: add | back | exit");

                        } else {
                            System.out.println("If you want details for a specific file, type the index of the file. Write `back` to go back. Write `exit` to close the app. ");
                            System.out.println("Or choose an action: add | rename | delete | move | copy");
                        }
                        response = scanner.nextLine();
                        if (response.equals("exit")) {
                            computer.writeDirectories(computer.getDirectories());

                            System.exit(0);
                        } else if (response.equals("back")) {
                            for (int i = 0; i < computer.getDirectories().size(); i++) {
                                System.out.println((i + 1) + ". " + computer.getDirectories().get(i).getPath());
                            }
                            System.out.println("Or choose an action: add | rename | delete");
                            location = "Directories";
                        } else if (response.equals("add")) {


                            System.out.println("Type the name of the new file: ");

                            String name = scanner.nextLine();

                            System.out.println("Write the type of the new file: ");

                            String type = scanner.nextLine();

                            File newFile = new File(name, type);

                            computer.getDirectories().get(directorNo).getFiles().add(newFile);

                            if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                System.out.println("-------------------------------------");

                                for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                }
                                System.out.println("-------------------------------------");

                            }

                        } else if (response.equals("move")) {


                            System.out.println("Choose the file you want to move in another location: ");

                            response = scanner.nextLine();
                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().get(directorNo).getFiles().size()) {
                                int fileNumber = (int) Double.parseDouble(response) - 1;

                                for (int i = 0; i < computer.getDirectories().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(i).getPath());
                                }


                                System.out.println("\nChoose the directory where you want to move the file: ");

                                response = scanner.nextLine();

                                if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {
                                    int destination = (int) Double.parseDouble(response) - 1;

                                    computer.getDirectories().get(destination).getFiles().add(computer.getDirectories().get(directorNo).getFiles().get(fileNumber));

                                    computer.getDirectories().get(directorNo).getFiles().remove(fileNumber);


                                    if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                        System.out.println("-------------------------------------");

                                        for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                            System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                        }
                                        System.out.println("-------------------------------------");

                                    }
                                } else {
                                    if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                        for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                            System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                        }
                                        System.out.println("-------------------------------------");

                                    }
                                    throw new MultimediaException("Wrong index. Try again!");

                                }
                            } else {
                                if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                    for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                        System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                    }
                                    System.out.println("-------------------------------------");

                                }
                                throw new MultimediaException("Wrong index. Try again!");
                            }

                        } else if (response.equals("copy")) {


                            System.out.println("Choose the file you want to copy in another location: ");

                            response = scanner.nextLine();
                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().get(directorNo).getFiles().size()) {

                                int fileNumber = (int) Double.parseDouble(response) - 1;

                                for (int i = 0; i < computer.getDirectories().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(i).getPath());
                                }

                                System.out.println("\nChoose the directory where you want to copy the file: ");

                                response = scanner.nextLine();

                                if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().size()) {

                                    int destination = (int) Double.parseDouble(response) - 1;

                                    computer.getDirectories().get(destination).getFiles().add(computer.getDirectories().get(directorNo).getFiles().get(fileNumber));

                                    if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                        System.out.println("-------------------------------------");

                                        for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                            System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                        }
                                        System.out.println("-------------------------------------");

                                    }
                                } else {
                                    if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                        for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                            System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                        }
                                        System.out.println("-------------------------------------");

                                    }
                                    throw new MultimediaException("Wrong Index! Try again.");
                                }

                            } else {
                                if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                    for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                        System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                    }
                                    System.out.println("-------------------------------------");

                                }
                                throw new MultimediaException("Wrong Index! Try again.");
                            }
                        } else if (response.equals("delete")) {


                            System.out.println("Choose the file you want to delete: ");

                            response = scanner.nextLine();

                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().get(directorNo).getFiles().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                // System.out.println(computer.getDirectories().get(directorNo).getFiles().get(resp));
                                List<File> newList = new ArrayList<>(computer.getDirectories().get(directorNo).getFiles());
                                newList.remove(resp);
                                computer.getDirectories().get(directorNo).setFiles(newList);
                                //computer.getDirectories().get(directorNo).getFiles().remove(resp);
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }

                            if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                System.out.println("-------------------------------------");

                                for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                }
                                System.out.println("-------------------------------------");

                            } else {
                                System.out.println("There are no files in this directory!");
                                System.out.println("-------------------------------------");

                                System.out.println("Choose an action: add | back | exit");
                            }

                        } else if (response.equals("rename")) {


                            System.out.println("Choose the file you want to rename: ");

                            response = scanner.nextLine();

                            if (Double.parseDouble(response) >= 1 && Double.parseDouble(response) <= computer.getDirectories().get(directorNo).getFiles().size()) {
                                int resp = (int) Double.parseDouble(response) - 1;
                                //  System.out.println(computer.getDirectories().get(directorNo).getFiles().get(resp));

                                System.out.println("Actual name: " + computer.getDirectories().get(directorNo).getFiles().get(resp).getName());

                                System.out.println("New name: ");

                                response = scanner.nextLine();

                                List<File> newList = new ArrayList<>(computer.getDirectories().get(directorNo).getFiles());
                                newList.get(resp).setName(response);
                                computer.getDirectories().get(directorNo).setFiles(newList);
                                //computer.getDirectories().get(directorNo).getFiles().remove(resp);
                            } else {
                                throw new MultimediaException("Invalid index! Try again.");
                            }

                            if (!computer.getDirectories().get(directorNo).getFiles().isEmpty()) {
                                System.out.println("-------------------------------------");

                                for (int i = 0; i < computer.getDirectories().get(directorNo).getFiles().size(); i++) {
                                    System.out.println(i + 1 + ". " + computer.getDirectories().get(directorNo).getFiles().get(i).getName());
                                }
                                System.out.println("-------------------------------------");

                            } else {
                                System.out.println("There are no files in this directory!");
                                System.out.println("-------------------------------------");

                                System.out.println("Choose an action: add | back | exit");
                            }

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

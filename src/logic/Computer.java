package logic;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer implements Serializable {
    List<Director> directories=new ArrayList<>();

    private static Computer computer=null;

    public Computer() {
    }

    public List<Director> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Director> directories) {
        this.directories = directories;
    }

    public void writeDirectories(List<Director> directoriesToWrite) throws Exception {

//        Director director1 = new Director(
//                "C:\\Users\\Documents\\",
//                Arrays.asList(
//                        new File(
//                                "Buletin",
//                                "jpg",
//                                4.3,
//                                LocalDateTime.now()
//                        ),
//                        new File(
//                                "AdeverintaStudent",
//                                "jpg",
//                                5.8,
//                                LocalDateTime.now()
//                        ),
//                        new File(
//                                "music",
//                                "mp3",
//                                10.5,
//                                LocalDateTime.now()
//                        ),
//                        new File(
//                                "photo",
//                                "png",
//                                12.8,
//                                LocalDateTime.now()
//                        )
//                )
//        );
//
//        Director director2 = new Director(
//                "C:\\Users\\Photos\\Others",
//                Arrays.asList(
//                        new File(
//                                "ss1",
//                                "jpg",
//                                8.7,
//                                LocalDateTime.now()
//                        ),
//                        new File(
//                                "Graduation",
//                                "png",
//                                9.6,
//                                LocalDateTime.now()
//                        ),
//                        new File(
//                                "Coldplay",
//                                "mp3",
//                                25.8,
//                                LocalDateTime.now()
//                        )
//                )
//        );
//
//        directories.add(director1);
//        directories.add(director2);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("computerData", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(directoriesToWrite);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDirectories() throws Exception
    {

        try {
            FileInputStream fileInputStream = new FileInputStream("computerData");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            directories=(ArrayList)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found!");
            c.printStackTrace();
            return;

        }
    }

    @Override
    public String toString() {
        return "Computer: \n" +
                "directories: " + directories +
                '\n';
    }

    public static synchronized Computer getInstance()
    {
        if(computer==null)
            computer=new Computer();
        return computer;
    }
}

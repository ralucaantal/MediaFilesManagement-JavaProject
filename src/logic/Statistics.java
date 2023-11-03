package logic;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Statistics implements Serializable {

    private static Statistics stats = null;

    public void ShowStatistics(Computer computer) {
        Director[] directors = new Director[computer.getDirectories().size()];
        //System.out.println("Dimensiune: " + computer.getDirectories().size());
        for (int i = 0; i < computer.getDirectories().size(); i++) {
            directors[i] = computer.getDirectories().get(i);
            // System.out.println(directors[i]);
        }

//        for (int i = 0; i < computer.getDirectories().size(); i++) {
//            //directors[i] = computer.getDirectories().get(i);
//            System.out.println(directors[i]);
//        }
        //System.out.println("Dimensiune: " + directors.length);
        System.out.println("------------------------------------------");

        int[][] filesTypes = new int[directors.length][5];


        for (int i = 0; i < directors.length; i++) {
//            System.out.println("Director: " + directors[i].getPath());
//            System.out.println("mp3 | wav |  jpg  |  png  | others");


            for (File f : directors[i].getFiles()) {
                switch (f.getType()) {
                    case "mp3" -> filesTypes[i][0] = filesTypes[i][0] + 1;
                    case "wav" -> filesTypes[i][1] = filesTypes[i][1] + 1;
                    case "jpg" -> filesTypes[i][2] = filesTypes[i][2] + 1;
                    case "png" -> filesTypes[i][3] = filesTypes[i][3] + 1;
                    default -> filesTypes[i][4] = filesTypes[i][4] + 1;
                }
            }


        }

        for (int j = 0; j < directors.length; j++) {
            System.out.println("Director: " + directors[j].getPath());

            System.out.println("mp3 | wav |  jpg  |  png  | others");

            for (int k = 0; k < 5; k++)
                System.out.print(filesTypes[j][k] + " | ");
            System.out.println();

            double dimension=0;
            LocalDateTime lastFile= LocalDateTime.MIN;
            for (File f : directors[j].getFiles()) {
                dimension+=f.getDimension();

                if(f.getDate().isAfter(lastFile))
                    lastFile=f.getDate();
            }

            System.out.println("Dimension: "+dimension);
            System.out.println("Last file: "+lastFile);
        }
    }

    public static synchronized Statistics getInstance() {
        if (stats == null)
            stats = new Statistics();
        return stats;
    }
}

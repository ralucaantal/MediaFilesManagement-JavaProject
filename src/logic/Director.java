package logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Director implements Serializable {

    private String path;

    List<File> files = new ArrayList<>();

    public Director(String path, List<File> files) {
        this.path = path;
        this.files = files;
    }

    @Override
    public String toString() {
        return "Director{" +
                "path='" + path + '\'' +
                ", files=" + files +
                '}';
    }
}

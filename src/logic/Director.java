package logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Director implements Serializable {

    private String path;

    List<File> files = new ArrayList<>();


    private static final long serialVersionUID = 1876203141525452896L;
    public Director(String path, List<File> files) {
        this.path = path;
        this.files = files;
    }

    public Director(String path) {
        this.path = path;
        this.files = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Director: " +'\n'+
                "Path: " + path + +'\n' +
                "Files: \n" + files +
                '\n';
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}

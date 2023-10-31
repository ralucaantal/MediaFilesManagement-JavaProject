package logic;

import java.io.Serializable;
import java.time.LocalDateTime;

public class File implements Serializable {

    String name;
    String type;
    double dimension;
    LocalDateTime date;

    public File(String name, String type, double dimension, LocalDateTime date) {
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "File: " +
                  name + '\n' +
                "Type: " + type + '\n' +
                "Dimension: " + dimension +'\n'+
                "Date: " + date +
                '\n';
    }
}

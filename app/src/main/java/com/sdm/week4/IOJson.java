package com.sdm.week4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class IOJson {
    public static IOJson single_instance = null;
    public static IOJson getInstance(){
        if (single_instance == null) {
            single_instance = new IOJson();
        }
        return single_instance;
    }

    public static String objectToFile(Object object) throws IOException {
        String path = "/res/raw/board.json";
        File data = new File(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(data));

        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return path;
    }

    public static Object objectFromFile(String path) throws IOException, ClassNotFoundException {
        Object object = null;
        File data = new File(path);
        if(data.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(data));
            object = objectInputStream.readObject();
            objectInputStream.close();
        }
        return object;
    }
}
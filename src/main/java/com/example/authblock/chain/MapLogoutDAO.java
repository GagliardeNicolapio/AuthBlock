package com.example.authblock.chain;

import java.io.*;
import java.util.HashMap;

public  class MapLogoutDAO {
    public static String readIdFromMap(String key) throws IOException {
        HashMap<String, String> hashMap = readMap();
        System.out.println("key: "+key.toLowerCase());
        String value = hashMap.get(key.toLowerCase());
        return value;
    }

    public static void insertInMap(String key, String value) throws IOException{
        HashMap<String,String> map = readMap();
        map.put(key.toLowerCase(),value.toLowerCase());
        writeMap(map);
    }

    private static HashMap<String, String> readMap() throws IOException{
        HashMap<String, String> hashMap;
        try {

            FileInputStream inputStream = new FileInputStream("src/main/java/com/example/authblock/mapLogoutRepository.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            hashMap = (HashMap<String, String>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        }catch (ClassNotFoundException | EOFException e ){
            hashMap = new HashMap<>();
        }
        return hashMap;
    }

    private static void writeMap(HashMap<String,String> map) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/example/authblock/mapLogoutRepository.txt");
        ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map);
        objectOutputStream.close();
        fileOutputStream.close();
    }

}

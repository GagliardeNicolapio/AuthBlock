package com.example.authblock.site;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class SitoClienteDAO {
    private Scanner file;
    private JSONArray array;

    public SitoClienteDAO() throws FileNotFoundException {
        this.file = new Scanner(new File("src/main/java/com/example/authblock/site/offBlockchain.txt"));
        array = new JSONArray(file.nextLine());
    }

    public void insert(JSONObject obj){
        array.put(array.length(), obj);
    }

    public void save() throws IOException {
        file.close();
        FileWriter writer = new FileWriter("src/main/java/com/example/authblock/site/offBlockchain.txt");
        writer.write(array.toString());
        writer.close();
        file = new Scanner(new File("src/main/java/com/example/authblock/site/offBlockchain.txt"));
    }


}

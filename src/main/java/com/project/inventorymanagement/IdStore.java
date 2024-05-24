package com.project.inventorymanagement;

import java.io.*;
import java.util.Scanner;


public class IdStore {

    public static int getIndex() {
        String dirPath = "data/";
        try {
            File directory = new File(dirPath);
            if (!directory.exists()) {
                boolean _ = directory.mkdirs();
            }
            String filePath = dirPath + "id.txt";
            File idFile = new File(filePath);
            if(!idFile.exists()) {
                PrintWriter fileWriter = new PrintWriter(idFile);
                fileWriter.println(2);
                fileWriter.close();
                return 1;
            }
            else{
                Scanner scanner = new Scanner(idFile);
                int id = scanner.nextInt();
                PrintWriter fileWriter = new PrintWriter(idFile);
                fileWriter.flush();
                fileWriter.println(id + 1);
                fileWriter.close();
                return id;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Integer.MAX_VALUE;
    }

}

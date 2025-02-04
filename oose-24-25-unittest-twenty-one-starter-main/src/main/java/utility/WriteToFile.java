package utility;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    FileWriter fileWriter;

    public WriteToFile(String fileName, String directory){
        fileWriter = openFile(fileName, directory);
    }

    public WriteToFile(String fileName){
        fileWriter = openFile(fileName, LoadCSV.defaultPath);
    }

    public FileWriter openFile(String fileName, String directory){
        directory = LoadCSV.formatDirectory(directory);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(directory + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileWriter;
    }

    public void writeToFile(String message){
        try {
            fileWriter.write(message + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void closeFile(){
        try {
            if ( fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



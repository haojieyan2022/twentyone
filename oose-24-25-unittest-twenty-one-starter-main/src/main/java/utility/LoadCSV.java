package utility;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;


public class LoadCSV {

    protected Scanner csvReader = null;
    public final static String defaultPath = "src/main/resources/";
    public static String formatDirectory(String directory){
        if (! directory.endsWith("/")){
            directory += "/";
        }
        return directory;
    }

    String configPath = null;

    protected Scanner createCSVreader(String configPath) throws Exception{
        Scanner csvReader = null;
        try {
            File playerFile = new File(configPath);
            csvReader = new Scanner(playerFile);
        } catch (IOException exc) {
            System.out.println("An error occurred.");
            exc.printStackTrace();
            throw exc;
        }
        return csvReader;
    }

    public void setCSVReader(Scanner csvReader, String configPath){
        this.csvReader = csvReader;
        this.configPath = configPath;
    }

    protected Scanner getCSVReader(String configPath) throws Exception{
        if (csvReader == null || configPath != this.configPath){
            csvReader = createCSVreader(configPath);
        }
        return csvReader;
    }

    public List<String> getRows(String fileName){
        List<String> rowData = new ArrayList<String>();
        try {
            Scanner csvReader = getCSVReader(fileName);
            while (csvReader.hasNextLine()) {
                rowData.add(csvReader.nextLine());
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return rowData;
    }

    protected List<String[]> stubDefaultList(){
        List<String[]> defaultNames = new ArrayList<String[]>();
        String[] name = {"Dealer From Stub"};
        defaultNames.add(name);
        return defaultNames;
    }

    public List<String[]> getCSVRows(String fileName){
        List<String[]> csvData = new ArrayList<String[]>();
        try {
            Scanner csvReader = getCSVReader(fileName);
            while (csvReader.hasNextLine()) {
                csvData.add(csvReader.nextLine().split(","));
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            csvData = stubDefaultList();
        }
        return csvData;
    }

}

package communication;

import utility.WriteToFile;

public class WriteInOut extends ConsoleInOut{

    WriteToFile inputWriteToFile;
    WriteToFile outputWriteToFile;

    public WriteInOut(String outputPrefix){
        inputWriteToFile = new WriteToFile(outputPrefix + ".input.csv");
        outputWriteToFile = new WriteToFile(outputPrefix + ".output.csv");
    }

    public String getString(){
        String input = super.getString();
        inputWriteToFile.writeToFile(input);
        return input;
    }

    public void print(String message){
        super.print(message);
        outputWriteToFile.writeToFile(message);
    }

    public void close(){
        inputWriteToFile.closeFile();
        outputWriteToFile.closeFile();
    }

    
}

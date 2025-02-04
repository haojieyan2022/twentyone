package card.entity;

import utility.WriteToFile;

public class WriteDeck extends Deck{

    public WriteDeck(String override, String saveToFilePrefix){
        super(override);
        WriteToFile writeToFile = new WriteToFile(saveToFilePrefix + ".deck.csv");
        writeToFile.writeToFile(this.toString());
        writeToFile.closeFile();
    }

    
}

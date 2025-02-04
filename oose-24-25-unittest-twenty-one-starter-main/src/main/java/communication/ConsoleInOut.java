package communication;

import java.util.Scanner;
import java.util.stream.Collectors;

import card.entity.Card;
import card.entity.Hand;
import card.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ConsoleInOut implements InOutInterface {

    public final static String ASK_DEFAULT_QUESTION = "Please select an item";
    protected final static String SELECT_CARD = "Please select a card";
    protected final static String WRONG_INDEX = "Please select an index in range.";
    protected final static String ASK_ENTER_INTEGER = "Please enter an integer";
    protected Scanner userInput = new Scanner(System.in);
    protected static boolean outputOn = true;
    protected boolean saveToFile = false; 

    public void setScanner(Scanner scanner){
        userInput = scanner;
    }

    public void setOutputOn(boolean on){
        outputOn = on;
    }

    public void print(String message) {
        if (outputOn){
            System.out.println(message);
        }
    }

    protected String getString() {
        return userInput.nextLine();
    }

    protected int getInteger() {
        int result = -1;
        int counter = 0;
        int maxCounter = 5;
        do {
            try {
                result = Integer.parseInt(getString());
            } catch (NumberFormatException exception) {
                // Display.Output expected NumberFormatException.
                print(ASK_ENTER_INTEGER);
            }
        } while (result < 0 && counter < maxCounter);

        return result;
    }

    public double getDouble(){
        double result = -1;
        int counter = 0;
        int maxCounter = 5;
        do {
            try {
                result = Double.parseDouble(getString());
            } catch (NumberFormatException exception) {
                // Display.Output expected NumberFormatException.
                print(ASK_ENTER_INTEGER);
            }
        } while (result < 0 && counter < maxCounter);

        return result;
    }

    public String getInputString(String message){
        print(message);
        return getString();        
    }

    public int getInputInteger(String message){
        print(message);
        return getInteger();
    }

    public int getInputIntegerBetween(String message, int minimum, int maximum){
        int result = 0;
        print(message);
        do  {
            result = getInteger();
        } while (result < minimum || result > maximum );
        return result;
    }

    public double getInputDouble(String message){
        print(message);
        return getDouble();
    }

    public int getListIndex(List<String> selection){
        return getListIndex(ASK_DEFAULT_QUESTION, selection);
    }

    public int getListIndex(String question, List<String> selection){
        int maxReplyCounter = 3;
        int replyCounter = 0;
        int listCounter = 0;
        int index = selection.size() + 1;
        print(question);
        for (String item : selection){
            print(listCounter + " - " + item);
            listCounter ++;
        }
        while (replyCounter < maxReplyCounter && index > listCounter){
            if (replyCounter > 0){
                print(WRONG_INDEX);
            }
            index = getInteger();
            replyCounter ++;
        }
        return index;
    }

    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(Class<ExtendedENum> selection){
        return getEnumIndex(ASK_DEFAULT_QUESTION, selection);
    }

    public <ExtendedENum extends Enum<ExtendedENum>> ExtendedENum getEnumIndex(String question, Class<ExtendedENum> selection){
        List<String> enumConstants = Arrays.stream(selection.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
        int index = getListIndex(question, enumConstants);
        return (ExtendedENum)selection.getEnumConstants()[index];
    }

    public YesOrNo getYesOrNo(String question){
        YesOrNo result = null;
        String answer = getInputString(question);
        if (answer.toLowerCase().contains("y") || answer.toLowerCase().contains("0")){
            result = YesOrNo.YES;
        } else {
            result = YesOrNo.NO;
        }
        return result;
    }

    public void displayPlayer(Player player){
        print(player.toString());
    }

    public Card getPlayersCard(Player player){
        displayPlayerWithVisibility(player);
        return player.playACard(getInputInteger(SELECT_CARD));
    }

    public void displayPlayerWithVisibility(Player player){
        print(player.displayHandWithVisibility());
    }

    public void displayCard(String name, Card card){
        print(name + " " + card.displayCamelCase());
    }

    public void displayHand(String name, Hand hand){
        print(name + " " + hand.toString());
    }

    public static void main(String[] args){
        ConsoleInOut consoleInOut = new ConsoleInOut();
        consoleInOut.print(consoleInOut.getEnumIndex("Hello", YesOrNo.class).toString());
    }

    public void close(){

    }
}

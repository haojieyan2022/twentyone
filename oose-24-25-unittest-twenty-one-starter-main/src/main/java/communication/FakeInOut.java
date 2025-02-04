package communication;

import java.util.ArrayList;
import java.util.List;

public class FakeInOut extends ConsoleInOut{

    private List<String> input;
    private List<String> output = new ArrayList<>();

    public FakeInOut(List<String> input){
        this.input = input;
    }

    public void print(String message) {
        output.add(message);
    }

    protected String getString(){
        String item = "";
        if (input.size() > 0){
            item = input.remove(0);
        }
        return item;
    }
    
    public List<String> getOutput(){
        return output;
    }
    
}

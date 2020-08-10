package user_input;

import java.util.Scanner;

/*
 * A Wrapper for the Scanner
 * 
 * Used to handle errors correctly and lightly.
 * It works in a series of consumers to manipulate
 * a given string (initial state)
 */

public class InputConsumer{
    private Scanner scan;

    public InputConsumer(Scanner scanner){
        this.scan = scanner;
    }

    public InputConsumer(String input){
        this.scan = new Scanner(input);
    }

    public String consumeString(String s){
        if(this.scan.hasNext(s)){
            this.scan.next(s);
            return s;
        }

        return null;
    }

    public Integer consumeInteger(){
        if(this.scan.hasNextInt()){
            return this.scan.nextInt();
        }

        return null;
    }

    public String consumeLine(){
        if(this.scan.hasNextLine()){
            return this.scan.nextLine();
        }

        return null;
    }
}

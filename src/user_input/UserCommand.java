package user_input;

import java.util.Scanner;
import java.util.HashMap;
import java.io.PrintStream;
import text_adventure.*;

public class UserCommand{

    public static Scanner consoleInput = new Scanner(System.in);
    public static PrintStream consoleOutput = System.out;

    // Lambda interface for all command selectors
    private interface CommandSelector{
        Command getCommand(Scanner scan);
    }

    public static HashMap<String, CommandSelector> initSelectors(){
        HashMap<String, CommandSelector> map = new HashMap<>();
        
        // Default to attack
        map.put("attack", scan -> {
                    scan.next("attack");
                    Command cmd = 
                        new Command();
                    
                    cmd.action = Command.ACTION.ATTACK;
                    return cmd;
                });

        // Get a number after item
        map.put("item", scan -> {
                    Command cmd = 
                        new Command();
                    InputConsumer consumer = new InputConsumer(scan);
                    consumer.consumeString("item");
                    Integer index = consumer.consumeInteger();
                    if(index != null){
                        cmd.action = Command.ACTION.SPECIAL;
                        cmd.index = index;
                        cmd.type = Command.TYPE.ITEM;
                        return cmd;
                    }

                    consoleOutput.println("Index could not be found!");
                    return null;
                });
        
        map.put("items", scan -> {
                    scan.next("items");
                    Command cmd = 
                        new Command();

                    cmd.action = Command.ACTION.LIST;
                    cmd.type = Command.TYPE.ITEM;
                    return cmd;
                });
        
        map.put("spells", scan -> {
                    scan.next("spells");
                    Command cmd = 
                        new Command();

                    cmd.action = Command.ACTION.LIST;
                    cmd.type = Command.TYPE.SPELL;
                    return cmd;
                });
        
        map.put("scan", scan -> {
                    scan.next("scan");
                    Command cmd = 
                        new Command();

                    cmd.action = Command.ACTION.DETAIL;
                    cmd.type = Command.TYPE.MONSTER;
                    return cmd;
                });
        
        map.put("define-spell", scan -> {
                    scan.next("define-spell");
                    Command cmd = 
                        new Command();
                    InputConsumer consumer = new InputConsumer(scan);
                    consumer.consumeString("define-spell");
                    String name = consumer.consumeLine().stripLeading();
                    if(name != null){
                        cmd.action = Command.ACTION.DETAIL;
                        cmd.name = name;
                        cmd.type = Command.TYPE.SPELL;
                        return cmd;
                    }

                    consoleOutput.println("We need the name of the spell!");
                    return null;
                });
        
        map.put("spell", scan -> {
                    scan.next("spell");
                    Command cmd = 
                        new Command();
                    InputConsumer consumer = new InputConsumer(scan);
                    consumer.consumeString("spell");
                    String name = consumer.consumeLine().stripLeading();
                    if(name != null){
                        cmd.action = Command.ACTION.SPECIAL;
                        cmd.name = name;
                        cmd.type = Command.TYPE.SPELL;
                        return cmd;
                    }

                    consoleOutput.println("Name of the spell could not be found!");
                    return null;
                });
        
        map.put("point-check", scan -> {
                    scan.next("point-check");
                    Command cmd = 
                        new Command();

                    cmd.action = Command.ACTION.DETAIL;
                    cmd.type = Command.TYPE.PLAYER;
                    return cmd;
                });
        
        return map;

    }

    private static final HashMap<String, CommandSelector> 
        validInput = initSelectors();


    public static Command getValidCommand(){
        Command cmd = null;
        System.out.println("Enter Action!");
		while (cmd == null) {
            
            // Check for any possible action
            for(String action : validInput.keySet()){
                if(consoleInput.hasNext(action)){
                    cmd = validInput.get(action).getCommand(consoleInput);
                    if(cmd != null){
                        break;
                    }
                }
            }

			if (cmd == null) {
                consoleOutput.println("=Available Actions=");
                for(String action : validInput.keySet()){
                    // TODO Make Objects for each Command rather
                    // than lambdas
                    consoleOutput.printf("  - \"%s\"\n", action);
                }
                
                while(consoleInput.nextLine().equals(""));
            }

            consoleInput.reset();
		}

        return cmd;
    }
}
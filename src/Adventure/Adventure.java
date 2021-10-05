package Adventure;

import java.util.Scanner;

public class Adventure {

    //The Game
    public void play(){
        Scanner scT = new Scanner(System.in);
        System.out.println("Please write your name to start the game");
        UserInterface userInterface = new UserInterface(scT.nextLine());
        userInterface.startGame();
        boolean running = true;
        while (running) {
            //Scenarios
            userInterface.scenarioCheck();

            //Menu
            String input = scT.nextLine();
            String command;
            String commandInput = "";
            if(input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
                commandInput = input.substring(input.indexOf(" ") + 1);
            }
            else{
                command=scanCommand(input);
            }
            switch (command) {
                case "go":
                    if(scanCommand(input).equals(command)){
                        userInterface.go(scanDirection(input));
                        break;
                    }
                    userInterface.go(scanDirection(commandInput));
                    break;
                case "look":
                    userInterface.look();
                    break;
                case "inventory":
                    userInterface.inventory();
                    break;
                case "time":
                    userInterface.time();
                    break;
                case "use":
                    userInterface.use(commandInput);
                    break;
                case "take":
                    userInterface.take(commandInput);
                    break;
                case "drop":
                    userInterface.drop(commandInput);
                    break;
                case "help":
                    userInterface.help();
                    break;
                case "exit":
                    userInterface.exit();
                    running = false;
                    break;
                default:
                    userInterface.invalidCommand(command);
            }
        }
    }

    public String scanDirection(String userInput) {
        if ((userInput.equalsIgnoreCase("North")) || (userInput.equalsIgnoreCase("N"))) {
            return "north";
        }
        else if ((userInput.equalsIgnoreCase("East")) || (userInput.equalsIgnoreCase("E"))) {
            return "east";
        }
        else if ((userInput.equalsIgnoreCase("South")) || (userInput.equalsIgnoreCase("S"))) {
            return "south";
        }
        else if ((userInput.equalsIgnoreCase("West")) || (userInput.equalsIgnoreCase("W"))) {
            return "west";
        }
        else return "invalid direction";
    }
    public String scanCommand(String userInput) {
        if ((userInput.equalsIgnoreCase("North")) || (userInput.equalsIgnoreCase("N"))) {
            return "go";
        }
        else if ((userInput.equalsIgnoreCase("East")) || (userInput.equalsIgnoreCase("E"))) {
            return "go";
        }
        else if ((userInput.equalsIgnoreCase("South")) || (userInput.equalsIgnoreCase("S"))) {
            return "go";
        }
        else if ((userInput.equalsIgnoreCase("West")) || (userInput.equalsIgnoreCase("W"))) {
            return "go";
        }
        else if ((userInput.equalsIgnoreCase("Inv")) || (userInput.equalsIgnoreCase("I"))){
            return "inventory";
        }
        else if ((userInput.equalsIgnoreCase("Take"))){
            return "take";
        }
        else if ((userInput.equalsIgnoreCase("drop"))){
            return "drop";
        }
        else if (userInput.equalsIgnoreCase("Look")) {
            return "look";
        }
        else if (userInput.equalsIgnoreCase("Help")) {
            return "help";
        }
        else if(userInput.equalsIgnoreCase("Restart")){
            return "restart";
        }
        else if (userInput.equalsIgnoreCase("Exit")) {
            return "exit";
        }
        else{return userInput;}
    }

    public static void main(String[] args) {
        Adventure newAdventure = new Adventure();
        newAdventure.play();
    }


}

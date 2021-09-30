package Adventure;

import java.util.Scanner;

public class Adventure {
    Player player = new Player();

    //The Game
    public void play(){
        player.getStarterRoom();
        Scanner name = new Scanner(System.in);
        System.out.println(startGameText());
        player.setPlayerName(name.nextLine());
        System.out.println(introText());
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            //Eventually put different criteria for scenarios
            discovery();
            //Start of the normal input menu
            String input = scanner.nextLine(); //user writes command
            String command;
            String commandInput = "";
            if(input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
                commandInput = input.substring(input.indexOf(" ") + 1);
            }
            else{
                command=scanCommand(input);
            }
            switch (command) { //Took this from our LampToggler3000 assignment
                case "go":
                    if(scanCommand(input).equals(command)){
                        go(input);
                        break;
                    }
                    go(commandInput);
                    break;
                case "look":
                    look();
                    break;
                case "inventory":
                    inventory();
                    break;
                case "take":
                    take(commandInput);
                    break;
                case "drop":
                    drop(commandInput);
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    exit();
                    running = false;
                    break;
                default:
                    System.out.println(invalidCommand(command));
            }
        }
    }

    //Scenarios
    public void discovery(){
        if(player.getDiscoveryEnd()==1){
            System.out.println(colorText(green,player.name+ " has discovered all rooms!"));
            player.discoveryEnd=2;
        }
    }

    //User Actions
    public void go(String input) {
        switch (scanDirection(input)) {
            case "north":
                if (player.playerRoom.north != null) {
                    player.playerRoom = player.playerRoom.north;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "east":
                if (player.playerRoom.east != null) {
                    player.playerRoom = player.playerRoom.east;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "south":
                if (player.playerRoom.south != null) {
                    player.playerRoom = player.playerRoom.south;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "west":
                if (player.playerRoom.west != null) {
                    player.playerRoom = player.playerRoom.west;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            default:
                System.out.println(invalidDirection(input));
                break;
        }
    }
    public void look(){
        System.out.println(lookText());
    }
    public void inventory(){
        if(player.inventory.size() == 0){
            System.out.println(inventoryListEmpty());
        } else {
            System.out.println(inventoryList());
        }
    }
    public void take(String input) {
        Items tempItem = player.playerRoom.findItem(input);
        if(tempItem!=null){
            if(player.itemWeightLimit(tempItem)) {
                player.takeItem(tempItem);
                System.out.println(takeItem(tempItem));
            }
            else{
                System.out.println(exceededWeight(tempItem));
            }
        }
        else {
            System.out.println(takeItem(input));
        }
    }
    public void drop(String input){
        Items tempItem = player.findItem(input);
        if(tempItem!=null){
            player.dropItem(tempItem);
            System.out.println(dropItem(tempItem));
        }
        else {
            System.out.println(dropItem(input));
        }


    }
    public void help(){
        System.out.println(helpText());
    }
    public void exit(){
        System.out.println(exitText());
    }

    //Scanners
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

    //Strings for Output
    //General CommandMenu
    public String startGameText(){
        return "Please write the name of your character to start the game";
    }
    public String introText() {
        return "Welcome to WAKE ME UP!\n" +
                colorText(cyan,
                        "\nAn evil hypnotist has put " + player.name + " in a hypnosis!"+
                                "\n" + player.name +  " can't wake up unless they solve the problems in his fantasy land 'Disturbia"+
                                "\nWrite directions to move around on the map (ex. north, n og go north)"+
                                "\nYou can write 'look' to get more details about the place you are at"+
                                "\nAnd if you need help, simply write 'help' to get a hint to solve the problem") +
                colorText(blue,"\nCurrently " + player.name + " is at " + player.playerRoom.roomName + ".") + "\nWrite a direction:";
    }
    public String invalidCommand(String command){
        return colorText(red,"Unknown command: " + command);
    }
    //Go Command
    public String travelsToNewRoom(){
        return colorText(blue,player.name+" travels to " + player.playerRoom.roomName);
    }
    public String invalidDirection(){
        return colorText(red,player.name+" cannot walk in this direction");
    }
    public String invalidDirection(String input){
        return colorText(red,input+" is not a valid direction");
    }
    //Look Command
    public String lookText(){
        player.setDiscovery();
        return player.name + " looks around\n" +
                colorText(cyan,player.playerRoom.roomDescription) + "\n" +
                roomItems();
    }
    public String roomItems(){
        if(player.playerRoom.items.size()==0){
            return colorText(red,"There are no items in " + player.playerRoom.roomName);
        }
        return "Items:" + colorText(blue,player.playerRoom.getItems());
    }
    //Inventory Command
    public String inventoryList(){
        return "Items in inventory:" +
                colorText(blue,player.getInventory());

    }
    public String inventoryListEmpty(){
        return colorText(red,player.name+"'s inventory is empty");
    }
    //Take Command
    public String takeItem(Items item){
        return colorText(green,"You pick up " + item.name);
    }
    public String takeItem(String item){
        return colorText(red,player.playerRoom.roomName + " does not contain " + item + " of any kind");
    }
    public String exceededWeight(Items item){
        return colorText(red,"You are not strong enough to pick up " + item.name);
    }
    //Drop Command
    public String dropItem(Items item){
        return colorText(yellow,"You drop " + item.name);
    }
    public String dropItem(String item){
        return colorText(red,"You do not have " + item);
    }
    //Help Command
    public String helpText(){
        return player.name + " asks for help" +
                white +"""
                Move Commands
                Go: Move player, North, East, South, West
                Look: Information of current location
                Inventory: Shows held items
                Take: Pick up an item at the location
                Drop: Drop an item at the location
                Help: Shows commands
                Exit: Exit the game
                """;
    }
    //Exit Command
    public String exitText(){
        return colorText(red,"Without warning " + player.name + " trips over some strange object in " + player.playerRoom.roomName + " and dies");
    }

    //Colors
    public String colorText(String color, String text){
        return color + text + resetColour;
    }
    String resetColour = "\u001B[0m";
    String black = "\u001B[30m";
    String red = "\u001B[31m";
    String green = "\u001B[32m";
    String yellow = "\u001B[33m";
    String blue = "\u001B[34m";
    String purple = "\u001B[35m";
    String cyan = "\u001B[36m";
    String white = "\u001B[37m";

    //main
    public static void main(String[] args) {
        Adventure newAdventure = new Adventure();
        newAdventure.play();
    }


}

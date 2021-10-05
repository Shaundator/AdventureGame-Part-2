package Adventure;

public class UserInterface {
   Player player;

    public UserInterface(String userName){
        this.player=new Player(userName);
    }
    public void startGame(){
        player.getStarterRoom();
        player.getStarterInventory();
        System.out.println(introText());
    }

    //Scenarios
    public void scenarioCheck(){
        discovery();
        timeLoss();
        homePath();
    }

    public void discovery(){
        if(player.getDiscoveryEnd()==1){
            System.out.println(colorText(green,player.name+ " has discovered all rooms!"));
            player.discoveryEnd=2;
        }
    }
    public void timeLoss(){
        if(player.map.getTime()>=12){
            System.out.println("It is midnight, and the primal instincts of " + player.name + " sets in...\n");
            if(player.inventory.contains(player.map.item10)&&player.inventory.contains(player.map.item11)){
                System.out.println(player.name + " finds an open spot " + inOrAtThe() + " " + player.playerRoom.roomName +
                        " and fall sleep with the help of " + player.map.item10.name + " and " + player.map.item11.name);
                player.map.resetTime();
                System.out.println(".\n.\n.");
                System.out.println(player.name + " wakes up and the hour is " + getTime());
            } else {
                System.out.println(colorText(cyan,"Cheers!") +
                        "\nas " + player.name + " uttered those words our hero lost to the midnight");
                System.exit(0);
            }
        }
    }
    public void homePath(){
        if(player.playerRoom==player.map.room3&&player.inventory.contains(player.map.item1)){
            System.out.println(colorText(green,player.map.item1.name + " starts lighting up and shows great power!")+
                    "\nA path to the east opens up, do you dare lead our hero into it?");
            player.map.openHome();
        }
    }

    //User Actions
    public void go(String input) {
        switch (input) {
            case "north":
                if (player.playerRoom.north != null) {
                    player.map.setTime();
                    player.playerRoom = player.playerRoom.north;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "east":
                if (player.playerRoom.east != null) {
                    player.map.setTime();
                    player.playerRoom = player.playerRoom.east;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "south":
                if (player.playerRoom.south != null) {
                    player.map.setTime();
                    player.playerRoom = player.playerRoom.south;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            case "west":
                if (player.playerRoom.west != null) {
                    player.map.setTime();
                    player.playerRoom = player.playerRoom.west;
                    System.out.println(travelsToNewRoom());
                } else {
                    System.out.println(invalidDirection());
                }
                break;
            default:
                System.out.println(colorText(red,input + " is not a valid direction"));
                break;
        }
    }
    public void look(){
        player.setDiscovery();
        System.out.println(player.name + " looks around\n" +
                colorText(cyan,player.playerRoom.roomDescription));
        System.out.println(roomItems());
    }
    public void inventory(){
        if(player.inventory.size() == 0){
            System.out.println(colorText(red,player.name+"'s inventory is empty"));
        } else {
            System.out.println("Items in inventory:" +
                    colorText(blue,getInventory()));
        }
    }
    public void take(String input){
        Items tempItem = player.playerRoom.findItem(input);
        if(tempItem!=null){
            if(player.itemWeightLimit(tempItem)) {
                player.takeItem(tempItem);
                System.out.println(colorText(green,player.name + " picks up the " + tempItem.name));
            }
            else{
                System.out.println(colorText(red,player.name + " is not strong enough to pick up the " + tempItem.name));
            }
        }
        else {
            System.out.println(colorText(red,player.playerRoom.roomName + " does not contain " + input + " of any kind"));
        }
    }
    public void drop(String input){
        Items tempItem = player.findItem(input);
        if(tempItem!=null){
            player.dropItem(tempItem);
            System.out.println(colorText(yellow,player.name + " drops the " + tempItem.name));
        }
        else {
            System.out.println(colorText(red,player.name + " does not have " + input));
        }
    }
    public void time(){
        System.out.println("The time is " + (12+player.map.getTime()) + ":00"+amOrPm());
    }

    public void help(){
        System.out.println(player.name + " asks for help\n" +
                colorText(white,"""
                Move Commands
                Go: Move player, North, East, South, West
                Look: Information of current location
                Time: Check the time
                Inventory: Shows held items
                Take: Pick up an item at the location
                Drop: Drop an item at the location
                Help: Shows commands
                Exit: Exit the game
                """));
    }
    public void exit(){
        System.out.println(colorText(red,"Without warning " + player.name + " trips over some strange object in " +
                player.playerRoom.roomName + " and truly becomes an alcoholic"));
    }

    //System Messages
    public void invalidCommand(String command){
        System.out.println(colorText(red,"Unknown command: " + command));
    }

    //Strings for Output
    public String introText(){
        return "Welcome to the journey of the Skejs\n"+
                colorText(cyan,"The time has come and " + player.name + " has done it again..." +
                        "\nOne bottle turned into many, and in the big city of Skejs our hero has lost his way" +
                        "\nWe need to get him home before the hour is night, or he will surely start drinking again," +
                        "\nyou are the only hope of showing him the way or all hope will be lost...") +
                "\nYou can write 'look' to see the details of " + player.name + "'s current location," +
                "\nyou can go north, east, south or west to get around."+
                "\nIf there are any further questions please write 'help'\n"+
                "\n"+colorText(cyan,"The time is 12:00pm, and our hero " + player.name + " finds himself sleeping on a bench"+
                "\nHe knows where he is, this is " + player.playerRoom.roomName);
    }
    public String travelsToNewRoom(){
        return colorText(blue,player.name+" travels to " + player.playerRoom.roomName);
    }
    public String invalidDirection(){
        return colorText(red,player.name+" cannot walk in this direction");
    }
    public String roomItems(){
        if(player.playerRoom.items.size()==0){
            return colorText(red,"There are no items in " + player.playerRoom.roomName);
        }
        return "Items:" + colorText(blue,getRoomItems());
    }
    public String getInventory(){
        int itemAmount = player.inventory.size();
        String result = "";
        for(int i=0; i<itemAmount; i++){
            result += "\n" + addArticleCap(player.inventory.get(i)) + "("+player.inventory.get(i).nameID+")";
        }
        return result;
    }
    public String getRoomItems() {
        String result = "";
        for(int i=0; i<player.playerRoom.items.size(); i++){
            result += "\n" + addArticleCap(player.playerRoom.items.get(i)) + "("+player.playerRoom.items.get(i).nameID+")";
        }
        return result;
    }
    public String getTime(){
        return (12+player.map.getTime()) + ":00"+amOrPm();
    }

    //Grammar
    public String addArticle(Items item){
        char letter = item.name.toLowerCase().charAt(0);
        return switch (letter) {
            case ('a'), ('e'), ('i'), ('o'), ('y'), ('u') -> "an " + item.name;
            default -> "a " + item.name;
        };
    }
    public String addArticleCap(Items item){
        char letter = item.name.toLowerCase().charAt(0);
        return switch (letter) {
            case ('a'), ('e'), ('i'), ('o'), ('y'), ('u') -> "An " + item.name;
            default -> "A " + item.name;
        };
    }
    public String amOrPm(){
        if(player.map.getTime()<0){
            return "am";
        }
        return "pm";
    }
    public String inOrAtThe(){
        int startIndex = player.playerRoom.roomName.indexOf(" ");
        String firstWord = player.playerRoom.roomName.substring(0,startIndex);
        if(firstWord.equalsIgnoreCase("the")){
            return "in";
        }
        if(firstWord.equalsIgnoreCase("a")){
            return "by";
        }
        return "at the";

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
}

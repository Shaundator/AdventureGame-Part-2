package Adventure;

public class Map {
    int time=0;
    int day=1;
    Room room1 = new Room("The Subway Station", "The train is loud, and so are the people walking around");
    Room room2 = new Room("The Train", "The train travels fast, yet it seems to do so in an eternity");
    Room room3 = new Room("A familiar yet unfamiliar place", "The place reminds of something, but what?");
    Room room4 = new Room("The Police Station", "You should avoid this place if you are up to mischief");
    Room room5 = new Room("A Prison", "Your future home? or perhaps it will stay a distant building");
    Room room6 = new Room("The School", "Well, there were lot of words");
    Room room7 = new Room("The Border", "What countries are beyond here, no one seems to know");
    Room room8 = new Room("Cocaine Street", "People walk around with cat eyes, you probably know why");
    Room room9 = new Room("A bench by a lake", "The best place to take a nap, especially during the night");
    Room room10 = new Room("Home", "Your home, you can barely recognise it");

    Items item1 = new Items("magnificent Rock","rock",2);
    Items item2 = new Items("collection of pokemon cards","cards",10);
    Items item3 = new Items("really big gun","gun",10);
    Items item4 = new Items("number 1","1",1);
    Items item5 = new Items("mobile phone","phone",5);
    Items item10 = new Items("ability to count sheep","sheep",2);
    Items item11 = new Items("tent","tent",15);


    //  1 = 2 = 3 = home
    //  |   X   |
    //  4 X 5 X 6
    //  |   |   |
    //  7 = 8 = 9

    public Map(){
    }

    public void openHome(){
        room3.setEast(room10);
    }
    public void setTime(){
        time++;
    }

    public void resetTime() {
        time = -4;
        day++;
    }

    public int getTime(){
        return time;
    }
    public void createMap(){
        room1.setEast(room2);
        room2.setEast(room3);
        room1.setSouth(room4);
        room3.setSouth(room6);
        room4.setSouth(room7);
        room5.setSouth(room8);
        room6.setSouth(room9);
        room7.setEast(room8);
        room8.setEast(room9);
    }

    public void putItems(){
        room1.addItem(item2);
        room1.addItem(item3);
        room2.addItem(item1);
        room2.addItem(item4);
        room5.addItem(item1);
        room4.addItem(item10);
        room4.addItem(item11);
    }



}

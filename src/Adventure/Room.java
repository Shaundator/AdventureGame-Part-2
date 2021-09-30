package Adventure;

import java.util.ArrayList;

public class Room {
    String roomName;
    String roomDescription;
    ArrayList<Items> items = new ArrayList();
    Room north;
    Room east;
    Room south;
    Room west;

    public Room(String roomName, String roomDescription){
        this.roomName=roomName;
        this.roomDescription=roomDescription;
    }

    public void addItem(Items item){
        items.add(item);
    }
    public void removeItem(Items item){
        items.remove(item);
    }

    public String getItems() {
        String result = "";
        for(int i=0; i<items.size(); i++){
            result += "\n" + items.get(i).name;
        }
        return result;
    }
    public Items findItem(String input){
        for(int i = 0; i < items.size(); i++ ){
            if(items.get(i).nameID.equals(input)){
                return items.get(i);
            }
        }
        return null;
    }

    public void setNorth(Room north){
        if(this.north==north){
            return;
        }
        if(north!=null) {
            this.north = north;
            north.setSouth(this);
        }
    }
    public void setEast(Room east){
        if(this.east==east){
            return;
        }
        if(east!=null) {
            this.east = east;
            east.setWest(this);
        }
    }
    public void setSouth(Room south){
        if(this.south==south){
            return;
        }
        if(south!=null) {
            this.south = south;
            south.setNorth(this);
        }
    }
    public void setWest(Room west){
        if(this.west==west){
            return;
        }
        if(west!=null) {
            this.west = west;
            west.setEast(this);
        }
    }
}

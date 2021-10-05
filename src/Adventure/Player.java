package Adventure;

import java.util.ArrayList;

public class Player {
    Map map = new Map();
    String name;
    Room playerRoom;
    int weightLimit = 20;
    ArrayList<Items> inventory = new ArrayList();

    public Player(String name){
        this.name=name;
    }
    public void getStarterRoom(){
        playerRoom=map.room1;
        map.createMap();
        map.putItems();
    }

    public void takeItem(Items item){
        for(int i=0; i<playerRoom.items.size(); i++){
            if(item==playerRoom.items.get(i)){
                inventory.add(item);
                playerRoom.removeItem(item);
            }
        }
    }
    public void dropItem(Items item){
        for(int i=0; i<inventory.size(); i++){
            if(item==inventory.get(i)){
                inventory.remove(item);
                playerRoom.addItem(item);
            }
        }
    }
    public Items findItem(String input){
        for(int i = 0; i < inventory.size(); i++ ){
            if(inventory.get(i).nameID.equals(input)){
                return inventory.get(i);
            }
        }
        return null;
    }


    public boolean itemWeightLimit(Items item){
        int currentWeight = 0;
        for(int i = 0; i < inventory.size(); i++){
            currentWeight+=inventory.get(i).weight;
            if(currentWeight>=weightLimit){
                return false;
            }
        }
        return true;
    }
    public int getDiscoveryEnd() {
        return discoveryEnd;
    } //equals 1 means all rooms has been discovered
    public void setDiscovery() {
        if(discoveryEnd==1){
            return;
        }
        for (int i = 0; i < discoveredRooms.length; i++) {
            if (discoveredRooms[i] == playerRoom) {
                return;
            }
        }
        for (int i = 0; i < discoveredRooms.length; i++){
            if (discoveredRooms[i]==null){
                discoveredRooms[i]=playerRoom;
                discovery++;
                i=1000;
            }
        }
        if(discovery==9){
            discoveryEnd++;
        }
    } //How many rooms has been discovered

    //Discovered Rooms
    Room[] discoveredRooms = new Room[9]; //Number of different rooms
    int discovery;
    int discoveryEnd;

}

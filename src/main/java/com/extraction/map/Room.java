package com.extraction.map;
import com.extraction.aliens.Alien;
import com.extraction.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Coordinate coordinate;
    private String title;
    private String description;
    private String iconPath = "";
    private Alien alien = null;
    private boolean is_closed = false;
    private List<Item> items = new ArrayList<Item>();

    public Room(){
        this.coordinate = new Coordinate(0, 0);
        this.title = "Title";
        this.description = "Description";
    }

    public Room(String room_name, Coordinate coordinate, String title, String description){
        this.coordinate = coordinate;
        this.title = title;
        this.description = description;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public List<Item> getItems() {return items;}

    public void addItem(Item item){items.add(item);}

    public void removeItem(Item itemToBeRemoved){items.remove(itemToBeRemoved);} //da modificare per dedidere sulla base di cosa (nome, id, ecc) rimuovere l'item

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getIconPath(){return iconPath;}

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Alien getAlien(){
        return alien;
    }

    public void setAlien(Alien alien){
        this.alien = alien;
    }

    public boolean isClosed(){
        return is_closed;
    }

    public void close(){
        is_closed = true;
    }

    public void open() { is_closed = false; }

}

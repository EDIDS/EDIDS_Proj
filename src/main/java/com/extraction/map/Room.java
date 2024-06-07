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
    private boolean is_dark = false;

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

    public void removeItem(String itemToThrow){
        Item item = this.findItem(itemToThrow);
        if (item != null /*&& item.isThrowable()*/) items.remove(item);
    }

    public Item findItem(String itemToFind){
        for (Item item : items) {
            if (item.getName().equals(itemToFind))
                return item;
        }
        return null;
    }

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

    public boolean isDark(){ return is_dark; }

    public void setDark(boolean is_dark){ this.is_dark = is_dark; }

    public void close(){
        is_closed = true;
    }

    public void open() { is_closed = false; }

}

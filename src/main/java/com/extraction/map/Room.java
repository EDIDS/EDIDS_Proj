package com.extraction.map;
import com.extraction.aliens.Alien;
import com.extraction.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The Room class represents a room in the game.
 * It contains a coordinate, title, description, icon path, an alien, a closed state, a list of items, and a dark state.
 */
public class Room {
    private Coordinate coordinate;
    private String title;
    private String description;
    private String iconPath = "";
    private Alien alien = null;
    private boolean is_closed = false;
    private List<Item> items = new ArrayList<Item>();
    private boolean is_dark = false;

    /**
     * Constructs a new Room with default values.
     */
    public Room(){
        this.coordinate = new Coordinate(0, 0);
        this.title = "Title";
        this.description = "Description";
    }

    /**
     * Constructs a new Room with the given coordinate, title, and description.
     * @param room_name The name of the room.
     * @param coordinate The coordinate of the room.
     * @param title The title of the room.
     * @param description The description of the room.
     */
    public Room(String room_name, Coordinate coordinate, String title, String description){
        this.coordinate = coordinate;
        this.title = title;
        this.description = description;
    }

    /**
     * Returns the coordinate of the room.
     * @return The coordinate of the room.
     */
    public Coordinate getCoordinate(){
        return coordinate;
    }

    /**
     * Returns the items in the room.
     * @return The items in the room.
     */
    public List<Item> getItems() {return items;}

    /**
     * Adds an item to the room.
     * @param item The item to be added.
     */
    public void addItem(Item item){items.add(item);}

    /**
     * Removes an item from the room.
     * @param itemToThrow The name of the item to be removed.
     */
    public void removeItem(String itemToThrow){
        Item item = this.findItem(itemToThrow);
        if (item != null /*&& item.isThrowable()*/) items.remove(item);
    }

    /**
     * Removes an item from the room.
     * @param itemToThrow The item to be removed.
     */
    public void removeItem(Item itemToThrow){
        Item item = this.findItem(itemToThrow);
        if (item != null /*&& item.isThrowable()*/) items.remove(item);
    }

    /**
     * Finds an item in the room.
     * @param itemToFind The name of the item to find.
     * @return The item if found, null otherwise.
     */
    public Item findItem(String itemToFind){
        for (Item item : items) {
            if (item.getName().equals(itemToFind))
                return item;
        }
        return null;
    }

    /**
     * Finds an item in the room.
     * @param itemToFind The item to find.
     * @return The item if found, null otherwise.
     */
    public Item findItem(Item itemToFind){
        for (Item item : items) {
            if (item == itemToFind)
                return item;
        }
        return null;
    }

    /**
     * Returns the title of the room.
     * @return The title of the room.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Returns the description of the room.
     * @return The description of the room.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns the icon path of the room.
     * @return The icon path of the room.
     */
    public String getIconPath(){return iconPath;}

    /**
     * Sets the icon path of the room.
     * @param iconPath The new icon path.
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * Sets the coordinate of the room.
     * @param coordinate The new coordinate.
     */
    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    /**
     * Sets the title of the room.
     * @param title The new title.
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Sets the description of the room.
     * @param description The new description.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Returns the alien in the room.
     * @return The alien in the room.
     */
    public Alien getAlien(){
        return alien;
    }

    /**
     * Sets the alien in the room.
     * @param alien The new alien.
     */
    public void setAlien(Alien alien){
        this.alien = alien;
    }

    /**
     * Checks if the room is closed.
     * @return True if the room is closed, false otherwise.
     */
    public boolean isClosed(){
        return is_closed;
    }

    /**
     * Checks if the room is dark.
     * @return True if the room is dark, false otherwise.
     */
    public boolean isDark(){ return is_dark; }

    /**
     * Sets the room to be dark or not.
     * @param is_dark The new dark state.
     */
    public void setDark(boolean is_dark){ this.is_dark = is_dark; }

    /**
     * Closes the room.
     */
    public void close(){
        is_closed = true;
    }

    /**
     * Opens the room.
     */
    public void open() { is_closed = false; }

}

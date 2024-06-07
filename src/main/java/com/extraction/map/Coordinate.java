package com.extraction.map;

/**
 * The Coordinate class represents a coordinate in the game map.
 * It contains a row and a column value.
 */
public class Coordinate {
    private int column;
    private int row;

    /**
     * Constructs a new Coordinate from a raw string.
     * The string should be in the format "row,column".
     * @param rawCoordinate The raw coordinate string.
     */
    public Coordinate(String rawCoordinate) {
        String[] parts = rawCoordinate.split(",");
        this.column = Integer.parseInt(parts[1]);
        this.row = Integer.parseInt(parts[0]);}

    /**
     * Constructs a new Coordinate with the given row and column values.
     * @param row The row value.
     * @param column The column value.
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the column value of the coordinate.
     * @return The column value.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the row value of the coordinate.
     * @return The row value.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the column value of the coordinate.
     * @param x The new column value.
     */
    public void setColumn(int x) {
        this.column = x;
    }

    /**
     * Sets the row value of the coordinate.
     * @param row The new row value.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Checks if this coordinate is equal to another object.
     * The other object is considered equal if it is also a Coordinate and has the same row and column values.
     * @param o The other object to compare.
     * @return True if the other object is equal to this coordinate, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (column != that.column) return false;
        if (row != that.row) return false;
        return true;
    }

    /**
     * Returns a string representation of the coordinate.
     * The string is in the format "(row, column)".
     * @return A string representation of the coordinate.
     */
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}


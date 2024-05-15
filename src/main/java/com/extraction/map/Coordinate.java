package com.extraction.map;

public class Coordinate {
    private int column;
    private int row;

    public Coordinate(String rawCoordinate) {
        String[] parts = rawCoordinate.split(",");
        this.column = Integer.parseInt(parts[1]);
        this.row = Integer.parseInt(parts[0]);}

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int x) {
        this.column = x;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (column != that.column) return false;
        if (row != that.row) return false;
        return true;
    }

    @Override
    public String toString() {
        return "(" + column + ", " + row + ")";
    }
}


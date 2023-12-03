package edu.project2;

public class Cell {
    private boolean bottom;
    private boolean top;
    private boolean right;
    private boolean left;

    public Cell() {
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getBottom() {
        return bottom;
    }

    public boolean getTop() {
        return top;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getLeft() {
        return left;
    }
}

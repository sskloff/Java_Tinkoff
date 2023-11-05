package edu.project2;

public class Cell {
    private boolean bottom;
    private boolean top;
    private boolean right;
    private boolean left;

    public Cell() {
        this.bottom = false;
        this.top = false;
        this.right = false;
        this.left = false;
    }

    public static Cell fabricCellCreator(boolean bottom, boolean top, boolean right, boolean left) {
        Cell cell = new Cell();
        cell.setRight(right);
        cell.setLeft(left);
        cell.setBottom(bottom);
        cell.setTop(top);
        return cell;
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

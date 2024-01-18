package edu.project2;

public class FabricCellCreator {

    public static Cell createCell(boolean bottom, boolean top, boolean right, boolean left) {
        Cell cell = new Cell();
        cell.setRight(right);
        cell.setLeft(left);
        cell.setBottom(bottom);
        cell.setTop(top);
        return cell;
    }
}

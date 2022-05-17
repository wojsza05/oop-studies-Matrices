package pl.edu.mimuw.matrix;

import java.util.Comparator;

public final class MatrixCellValue {

  public final int row;
  public final int column;
  public final double value;

  public MatrixCellValue(int row, int column, double value) {
    this.column = column;
    this.row = row;
    this.value = value;
  }

  @Override
  public String toString() {
    return "{" + value + " @[" + row + ", " + column + "]}";
  }

  public static MatrixCellValue cell(int row, int column, double value) {
    return new MatrixCellValue(row, column, value);
  }

  public static class CellValueComparator implements Comparator<MatrixCellValue> {
    public int compare(MatrixCellValue a, MatrixCellValue b) {
      if (a.row < b.row)
        return -1;
      if (a.row > b.row)
        return 1;

      if (a.column < b.column)
        return -1;
      if (a.column > b.column)
        return 1;

      return Double.compare(a.value, b.value);
    }
  }
}

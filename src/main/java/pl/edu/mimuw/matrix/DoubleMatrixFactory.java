package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

    // TODO Dodać testy, zwłaszcza na macierze constant, row, column, vector, których nie ma w testach.

    private DoubleMatrixFactory() {
    }

    public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values){
        return new SparseMatrix(shape, values);
    }

    public static IDoubleMatrix full(double[][] values) {
        return new FullMatrix(values);
    }

    public static IDoubleMatrix identity(int size) {
        return new IdentityMatrix(size);
    }

    public static IDoubleMatrix diagonal(double... diagonalValues) {
        return new DiagonalMatrix(diagonalValues);
    }

    public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
        return new AntiDiagonalMatrix(antiDiagonalValues);
    }

    public static IDoubleMatrix vector(double... values){
        double[][] matrixValues = new double[values.length][];
        for (int i = 0; i < values.length; i++) {
            matrixValues[i] = new double[1];
            matrixValues[i][0] = values[i];
        }
        return new FullMatrix(matrixValues);
    }

    public static IDoubleMatrix zero(Shape shape) {
        return new ZeroMatrix(shape);
    }

    public static IDoubleMatrix constant(Shape shape, double value) {
        return new ConstantMatrix(shape, value);
    }

    public static IDoubleMatrix rowMatrix(Shape shape, double... values) {
        return new RowMatrix(shape, values);
    }

    public static IDoubleMatrix columnMatrix(Shape shape, double... values) {
        return new ColumnMatrix(shape, values);
    }
}

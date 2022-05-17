package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class IdentityMatrix extends RegularSparseMatrix {

    public IdentityMatrix(int size) {
        assert size > 0;
        shape = matrix(size, size);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[] resultValues = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++)
            resultValues[i] = scalar;

        return new DiagonalMatrix(resultValues);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;

        if (other.getClass() == ZeroMatrix.class)
            return new ZeroMatrix(matrix(shape.rows, other.shape().columns));

        if (other.getClass() == DiagonalMatrix.class
                || other.getClass() == IdentityMatrix.class) {
            double[] resultValues = new double[values.length];

            for (int i = 0; i < values.length; i++)
                resultValues[i] = get(i, i) * other.get(i, i);

            return new DiagonalMatrix(resultValues);
        }
        return super.times(other);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return (row == column ? 1 : 0);
    }

    @Override
    public double normOne() {
        return 1;
    }

    @Override
    public double normInfinity() {
        return 1;
    }

    @Override
    public double frobeniusNorm() {
        return Math.sqrt(shape.rows);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dimensions: ");
        sb.append(shape.rows).append(" x ").append(shape.columns).append("\n");
        for (int y = 0; y < shape.rows; y++) {
            sb.append("[ ");

            if (y < 3)
                sb.append("0 ".repeat(y));
            else
                sb.append("0 ... 0 ");

            sb.append(get(y, y)).append(" ");

            if (y >= shape.rows - 3)
                sb.append("0 ".repeat(Math.max(0, shape.rows - (y + 1))));
            else
                sb.append("0 ... 0 ");

            sb.append("]\n");
        }
        return sb.toString();
    }
}

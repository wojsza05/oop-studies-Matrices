package pl.edu.mimuw.matrix;

public class IdentityMatrix extends RegularSparseMatrix {

    public IdentityMatrix(int size) {
        assert size > 0;
        shape = Shape.matrix(size, size);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        if (scalar == 1)
            return this;

        double[] resultValues = new double[shape.rows];
        for (int i = 0; i < shape.rows; i++)
            resultValues[i] = scalar;

        return new DiagonalMatrix(resultValues);
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

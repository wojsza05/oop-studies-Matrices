package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class DiagonalMatrix extends RegularSparseMatrix {

    public DiagonalMatrix(double... diagonalValues) {
        super(diagonalValues);
        shape = matrix(values.length, values.length);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return new DiagonalMatrix(multiplyElementsOfTheArray(values, scalar));
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;

        if (other.getClass() == ZeroMatrix.class)
            return new ZeroMatrix(matrix(shape.rows, other.shape().columns));

        if (getClass() == DiagonalMatrix.class && other.getClass() == DiagonalMatrix.class
                || other.getClass() == IdentityMatrix.class) {
            double[] resultValues = new double[values.length];

            for (int i = 0; i < values.length; i++)
                resultValues[i] = get(i, i) * other.get(i, i);

            return new DiagonalMatrix(resultValues);
        }
        return super.times(other);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        if (getClass() == other.getClass())
            return new DiagonalMatrix(sumTwoArrays(values, ((DiagonalMatrix) other).values));
        return super.plus(other);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return (row == column ? values[row] : 0);
    }

    @Override
    public double normOne() {
        if (normOneValue == -1)
            for (double val: values)
                normOneValue = Math.max(normOneValue, Math.abs(val));
        return normOneValue;
    }

    @Override
    public double normInfinity() {
        return normOne();
    }

    @Override
    public double frobeniusNorm() {
        if (frobeniusNormValue == -1) {
            double sum = 0;
            for (double val: values)
                sum += val * val;
            frobeniusNormValue = Math.sqrt(sum);
        }
        return frobeniusNormValue;
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

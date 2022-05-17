package pl.edu.mimuw.matrix;

import java.util.Arrays;

public abstract class AbstractMatrix implements IDoubleMatrix {
    protected Shape shape;
    protected double normOneValue = -1;
    protected double normInfinityValue = -1;
    protected double frobeniusNormValue = -1;

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;
        double[][] resultMatrix = new double[shape.rows][other.shape().columns];
        double[][] thisMatrix = this.data();
        double[][] otherMatrix = other.data();

        for (int y = 0; y < shape.rows; y++)
            for (int x = 0; x < other.shape().columns; x++)
                for (int i = 0; i < shape.columns; i++)
                    resultMatrix[y][x] += thisMatrix[y][i] * otherMatrix[i][x];

        return new FullMatrix(resultMatrix);
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());
        double[][] resultMatrix = this.data();
        double[][] otherMatrix = other.data();

        for (int y = 0; y < shape.rows; y++)
            for (int x = 0; x < shape.columns; x++)
                resultMatrix[y][x] += otherMatrix[y][x];

        return new FullMatrix(resultMatrix);
    }

    @Override
    public IDoubleMatrix plus(double scalar) {
        if (scalar == 0)
            return this;

        double[][] resultMatrix = this.data();

        for (int y = 0; y < shape.rows; y++)
            for (int x = 0; x < shape.columns; x++)
                resultMatrix[y][x] += scalar;

        return new FullMatrix(resultMatrix);
    }

    @Override
    public IDoubleMatrix minus(IDoubleMatrix other) {
        assert other != null;
        return plus(other.times(-1));
    }

    @Override
    public IDoubleMatrix minus(double scalar) {
        return plus(-scalar);
    }

    @Override
    public Shape shape() {
        //return shape;
        return Shape.matrix(shape.rows, shape.columns);
    }

    @Override
    public String toString() {
        double[][] values = this.data();
        StringBuilder sb = new StringBuilder();

        sb.append("Dimensions: ");
        sb.append(shape.rows).append(" x ").append(shape.columns).append("\n");

        for (int y = 0; y < shape.rows; y++) {
            sb.append("[ ");
            for (int x = 0; x < shape.columns; x++)
                sb.append(values[y][x]).append(" ");
            sb.append("]\n");
        }

        return sb.toString();
    }

    protected static double[][] copyTwoDimensionalArray(double[][] array) {
        double[][] resultArray = new double[array.length][];
        for (int y = 0; y < array.length; y++)
            resultArray[y] = Arrays.copyOf(array[y], array[y].length);
        return resultArray;
    }
}

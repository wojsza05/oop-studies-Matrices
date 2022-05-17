package pl.edu.mimuw.matrix;

import java.util.Arrays;

public abstract class RegularSparseMatrix extends AbstractMatrix {
    protected double[] values;

    protected RegularSparseMatrix(double... values) {
        assert values != null;
        this.values = Arrays.copyOf(values, values.length);
    }

    @Override
    public double[][] data() {
        double[][] resultMatrix = new double[shape.rows][shape.columns];

        for (int y = 0; y < shape.rows; y++)
            for (int x = 0; x < shape.columns; x++)
                resultMatrix[y][x] = get(y, x);

        return resultMatrix;
    }

    protected double[] multiplyElementsOfTheArray(double[] array, double scalar) {
        double[] resultValues = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++)
            resultValues[i] *= scalar;
        return resultValues;
    }
}

package pl.edu.mimuw.matrix;

public class FullMatrix extends AbstractMatrix {
    private final double[][] values;

    public FullMatrix(double[][] values) {
        assert values != null;
        assert values.length > 0;
        assert values[0].length > 0;
        for (int i = 1; i < values.length; i++)
            assert values[i].length == values[0].length;

        shape = Shape.matrix(values.length, values[0].length);
        this.values = copyTwoDimensionalArray(values);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        double[][] resultMatrix = copyTwoDimensionalArray(this.values);

        for (int y = 0; y < shape.rows; y++)
            for (int x = 0; x < shape.columns; x++)
                resultMatrix[y][x] *= scalar;

        return new FullMatrix(resultMatrix);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return values[row][column];
    }

    @Override
    public double[][] data() {
        return copyTwoDimensionalArray(this.values);
    }

    @Override
    public double normOne() {
        if (normOneValue == -1) {
            for (int x = 0; x < shape.columns; x++) {
                double sum = 0;
                for (int y = 0; y < shape.rows; y++)
                    sum += Math.abs(values[y][x]);
                normOneValue = Math.max(normOneValue, sum);
            }
        }
        return normOneValue;
    }

    @Override
    public double normInfinity() {
        if (normInfinityValue == -1) {
            for (int y = 0; y < shape.rows; y++) {
                double sum = 0;
                for (int x = 0; x < shape.columns; x++)
                    sum += Math.abs(values[y][x]);
                normInfinityValue = Math.max(normInfinityValue, sum);
            }
        }
        return normInfinityValue;
    }

    @Override
    public double frobeniusNorm() {
        if (frobeniusNormValue == -1) {
            double sum = 0;
            for (int x = 0; x < shape.columns; x++)
                for (int y = 0; y < shape.rows; y++)
                    sum += (values[y][x] * values[y][x]);
            frobeniusNormValue = Math.sqrt(sum);
        }
        return frobeniusNormValue;
    }
}

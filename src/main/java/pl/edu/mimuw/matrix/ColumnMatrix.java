package pl.edu.mimuw.matrix;

public class ColumnMatrix extends RegularSparseMatrix {
    public ColumnMatrix(Shape shape, double... values) {
        super(values);
        assert values.length == shape.rows;
        this.shape = Shape.matrix(shape.rows, shape.columns);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        if (scalar == 1)
            return this;
        return new ColumnMatrix(shape, multiplyElementsOfTheArray(values, scalar));
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return values[row];
    }

    @Override
    public double normOne() {
        if (normOneValue == -1) {
            for (int y = 0; y < shape.rows; y++)
                normOneValue += Math.abs(values[y]);
        }
        return normOneValue;
    }

    @Override
    public double normInfinity() {
        if (normInfinityValue == -1) {
            for (int y = 0; y < shape.rows; y++)
                normInfinityValue = Math.max(normInfinityValue, Math.abs(values[y]));
            normInfinityValue *= shape.columns;
        }
        return normInfinityValue;
    }

    @Override
    public double frobeniusNorm() {
        if (frobeniusNormValue == -1) {
            for (int y = 0; y < shape.rows; y++)
                frobeniusNormValue += values[y] * values[y];
            frobeniusNormValue = Math.sqrt(frobeniusNormValue * shape.columns);
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
            if (shape.columns < 3)
                for (int x = 0; x < shape.columns; x++) {
                    sb.append(values[y]).append(" ");
                }
            else
                sb.append(values[y]).append(" ... ").append(values[y]).append(" ");
            sb.append("]\n");
        }
        return sb.toString();
    }
}

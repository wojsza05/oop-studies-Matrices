package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class RowMatrix extends RegularSparseMatrix {
    public RowMatrix(Shape shape, double... values) {
        super(values);
        assert values.length == shape.rows;
        this.shape = matrix(shape.rows, shape.columns);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        if (scalar == 1)
            return this;
        return new RowMatrix(shape, multiplyElementsOfTheArray(values, scalar));
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return values[column];
    }

    @Override
    public double normOne() {
        if (normOneValue == -1) {
            for (int x = 0; x < shape.columns; x++)
                normOneValue = Math.max(normOneValue, Math.abs(values[x]));
            normOneValue *= shape.rows;
        }
        return normOneValue;
    }

    @Override
    public double normInfinity() {
        if (normInfinityValue == -1) {
            for (int x = 0; x < shape.columns; x++)
                normInfinityValue += Math.abs(values[x]);
        }
        return normInfinityValue;
    }

    @Override
    public double frobeniusNorm() {
        if (frobeniusNormValue == -1) {
            for (int x = 0; x < shape.columns; x++)
                frobeniusNormValue += values[x] * values[x];
            frobeniusNormValue = Math.sqrt(frobeniusNormValue * shape.rows);
        }
        return frobeniusNormValue;
    }
}

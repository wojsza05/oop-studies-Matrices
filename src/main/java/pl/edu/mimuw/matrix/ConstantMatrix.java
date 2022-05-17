package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class ConstantMatrix extends RegularSparseMatrix {
    protected double value;

    public ConstantMatrix(Shape shape, double value) {
        this.value = value;
        this.shape = matrix(shape.rows, shape.columns);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return new ConstantMatrix(shape, scalar * value);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return value;
    }

    @Override
    public double normOne() {
        return shape.rows * Math.abs(value);
    }

    @Override
    public double normInfinity() {
        return shape.columns * Math.abs(value);
    }

    @Override
    public double frobeniusNorm() {
        return Math.sqrt(shape.columns * shape.rows * value * value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dimensions: ");
        sb.append(shape.rows).append(" x ").append(shape.columns).append("\n");
        for (int y = 0; y < shape.rows; y++) {
            sb.append("[ ");
            if (shape.columns < 3)
                for (int x = 0; x < shape.columns; x++)
                    sb.append(value).append(" ");
            else
                sb.append(value).append(" ... ").append(value).append(" ");
            sb.append("]\n");
        }
        return sb.toString();
    }
}

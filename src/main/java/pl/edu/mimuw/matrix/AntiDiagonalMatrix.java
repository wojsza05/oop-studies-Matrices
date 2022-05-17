package pl.edu.mimuw.matrix;

public class AntiDiagonalMatrix extends DiagonalMatrix {
    public AntiDiagonalMatrix(double... antiDiagonalValues) {
        super(antiDiagonalValues);
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        return new AntiDiagonalMatrix(multiplyElementsOfTheArray(values, scalar));
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        if (getClass() == other.getClass())
            return new AntiDiagonalMatrix(sumTwoArrays(values, ((AntiDiagonalMatrix) other).values));
        return super.plus(other);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);
        return (row + column == values.length + 1 ? values[row] : 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dimensions: ");
        sb.append(shape.rows).append(" x ").append(shape.columns).append("\n");
        for (int y = 0; y < shape.rows; y++) {
            sb.append("[ ");

            if (y >= shape.rows - 3)
                sb.append("0 ".repeat(Math.max(0, shape.rows - (y + 1))));
            else
                sb.append("0 ... 0 ");

            sb.append(values[y]).append(" ");

            if (y < 3)
                sb.append("0 ".repeat(y));
            else
                sb.append("0 ... 0 ");

            sb.append("]\n");
        }
        return sb.toString();
    }
}

package pl.edu.mimuw.matrix;

import java.util.Arrays;

public class SparseMatrix extends AbstractMatrix {
    private final MatrixCellValue[] cellValues;

    public SparseMatrix(Shape shape, MatrixCellValue... values) {
        assert shape != null;
        for (MatrixCellValue cellValue: values) {
            assert cellValue != null;
            shape.assertInShape(cellValue.row, cellValue.column);
        }

        this.cellValues = Arrays.copyOf(values, values.length);
        Arrays.sort(this.cellValues, new MatrixCellValue.CellValueComparator());
        this.shape = Shape.matrix(shape.rows, shape.columns);

        for (int i = 1; i < this.cellValues.length; i++)
            assert (this.cellValues[i].row != this.cellValues[i - 1].row
                    || this.cellValues[i].column != this.cellValues[i - 1].column);
    }

    private MatrixCellValue[] getCellValues() {
        return Arrays.copyOf(cellValues, cellValues.length);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;
        return super.times(other); // TODO Zmienić
    }

    @Override
    public IDoubleMatrix plus(IDoubleMatrix other) {
        assert other != null;
        assert shape.equals(other.shape());

        if (getClass() == other.getClass()) {
            MatrixCellValue[] resultValues;
            int count = cellValues.length;

            for (MatrixCellValue cv: ((SparseMatrix)other).getCellValues())
                if (this.get(cv.row, cv.column) == 0)
                    count++;

            resultValues = new MatrixCellValue[count];
            int position = 0;
            for (MatrixCellValue cv: cellValues)
                resultValues[position++] = MatrixCellValue.cell(cv.row,
                        cv.column, cv.value + other.get(cv.row, cv.column));

            for (MatrixCellValue cv: ((SparseMatrix)other).getCellValues())
                if (this.get(cv.row, cv.column) == 0)
                    resultValues[position++] = MatrixCellValue.cell(cv.row, cv.column, cv.value);

            return new SparseMatrix(shape, resultValues);
        }
        else {
            return super.plus(other);
        }
    }

    @Override
    public IDoubleMatrix times(double scalar) {
        MatrixCellValue[] newValues = new MatrixCellValue[cellValues.length];

        for (int i = 0; i < cellValues.length; i++)
            newValues[i] = MatrixCellValue.cell(cellValues[i].row,
                    cellValues[i].column, cellValues[i].value * scalar);

        return new SparseMatrix(shape, newValues);
    }

    @Override
    public double get(int row, int column) {
        shape.assertInShape(row, column);

        int left = 0;
        int right = cellValues.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            MatrixCellValue cv = cellValues[middle];
            if (cv.row > row || (cv.row == row && cv.column >= column))
                right = middle;
            else
                left = middle + 1;
        }
        MatrixCellValue cv = cellValues[left];
        if (cv.row == row && cv.column == column)
            return cv.value;
        return 0;
    }

    @Override
    public double[][] data() {
        double[][] resultMatrix = new double[shape.rows][shape.columns];

        for (MatrixCellValue cellValue: cellValues)
            resultMatrix[cellValue.row][cellValue.column] = cellValue.value;

        return resultMatrix;
    }

    @Override
    public double normOne() {
        if (normOneValue == -1) {
            double[] sumsInColumns = new double[shape.columns];
            for (MatrixCellValue cv: cellValues)
                sumsInColumns[cv.column] += Math.abs(cv.value);

            for (double sum: sumsInColumns)
                normOneValue = Math.max(normOneValue, sum);
        }
        return normOneValue;
    }

    @Override
    public double normInfinity() {
        if (normInfinityValue == -1) {
            double[] sumsInRows = new double[shape.rows];
            for (MatrixCellValue cv: cellValues)
                sumsInRows[cv.row] += Math.abs(cv.value);

            for (double sum: sumsInRows)
                normInfinityValue = Math.max(normInfinityValue, sum);
        }
        return normInfinityValue;
    }

    @Override
    public double frobeniusNorm() {
        if (frobeniusNormValue == -1) {
            double sum = 0;
            for (MatrixCellValue cv: cellValues)
                sum += cv.value * cv.value;
            frobeniusNormValue = Math.sqrt(sum);
        }
        return frobeniusNormValue;
    }
}

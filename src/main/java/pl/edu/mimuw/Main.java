package pl.edu.mimuw;

import pl.edu.mimuw.matrix.IDoubleMatrix;
import pl.edu.mimuw.matrix.MatrixCellValue;
import pl.edu.mimuw.matrix.Shape;

import static pl.edu.mimuw.matrix.DoubleMatrixFactory.*;
import static pl.edu.mimuw.matrix.MatrixCellValue.cell;

public class Main {

    public static void main(String[] args) {
        double[][] exampleMatrix = new double[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++)
                exampleMatrix[y][x] = (y * 10. + x) / 10.;
        }

        MatrixCellValue[] cellValues = new MatrixCellValue[15];
        int position = 0;
        for (int i = 0; i < 15; i++) {
            cellValues[position] = cell(7 * position / 10,
                    (7 * position) % 10, 7 * position + 3);
            position++;
        }

        double[] values = new double[10];
        for (int i = 0; i < 10; i++)
            values[i] = i + 1;

        Shape shape = Shape.matrix(10, 10);

        System.out.println("Full matrix:");
        IDoubleMatrix exampleFull = full(exampleMatrix);
        System.out.println(exampleFull);

        System.out.println("Sparse Matrix:");
        IDoubleMatrix exampleSparse = sparse(shape, cellValues);
        System.out.println(exampleSparse);

        System.out.println("Diagonal Matrix:");
        IDoubleMatrix exampleDiagonal = diagonal(values);
        System.out.println(exampleDiagonal);

        System.out.println("Anti-diagonal Matrix:");
        IDoubleMatrix exampleAntiDiagonal = antiDiagonal(values);
        System.out.println(exampleAntiDiagonal);

        System.out.println("Identity Matrix:");
        IDoubleMatrix exampleIdentity = identity(10);
        System.out.println(exampleIdentity);

        System.out.println("Constant Matrix:");
        IDoubleMatrix exampleConstant = constant(shape, 5);
        System.out.println(exampleConstant);

        System.out.println("Zero Matrix:");
        IDoubleMatrix exampleZero = zero(shape);
        System.out.println(exampleZero);

        System.out.println("Column Matrix:");
        IDoubleMatrix exampleColumn = columnMatrix(shape, values);
        System.out.println(exampleColumn);

        System.out.println("Row Matrix:");
        IDoubleMatrix exampleRow = rowMatrix(shape, values);
        System.out.println(exampleRow);

        System.out.println("Vector:");
        IDoubleMatrix exampleVector = vector(values);
        System.out.println(exampleVector);
    }
}

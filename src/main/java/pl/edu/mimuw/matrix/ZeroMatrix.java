package pl.edu.mimuw.matrix;

import static pl.edu.mimuw.matrix.Shape.matrix;

public class ZeroMatrix extends ConstantMatrix {
    public ZeroMatrix(Shape shape) {
        super(shape, 0);
    }

    @Override
    public IDoubleMatrix times(IDoubleMatrix other) {
        assert other != null;
        assert shape.columns == other.shape().rows;

        return new ZeroMatrix(matrix(shape.rows, other.shape().columns));
    }
}

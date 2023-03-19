package org.kos.util.either;

public class Right<L, R> extends Either<L, R> {
    public Right(R right) {
        super(null, right);
    }
}

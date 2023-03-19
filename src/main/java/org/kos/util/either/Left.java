package org.kos.util.either;

public class Left<L, R> extends Either<L, R> {
    public Left(L left) {
        super(left, null);
    }
}

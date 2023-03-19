package org.kos.util.either;

import java.util.Optional;
import java.util.function.Function;

public class Either<L, R> {
    private final L left;
    private final R right;

    public Optional<L> left() {
        return Optional.ofNullable(left);
    }

    public Optional<R> right() {
        return Optional.ofNullable(right);
    }

    public Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> ofLeft(L l) {
        return new Left<>(l);
    }

    public static <L, R> Either<L, R> ofRight(R r) {
        return new Right<>(r);
    }


    public Boolean isLeft() {
        return left != null;
    }


    public Boolean isRight() {
        return right != null;
    }

    <R2> Either<L, R2> map(Function<R, R2> f) {
        if (isRight()) {
            return new Either<L, R2>(null, f.apply(this.right));
        } else return new Either<>(this.left, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Either<?, ?> either = (Either<?, ?>) o;

        if (left != null ? !left.equals(either.left) : either.left != null) return false;
        return right != null ? right.equals(either.right) : either.right == null;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Either{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
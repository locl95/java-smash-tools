package org.kos.util.either;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EitherTest {
    @Test
    public void GivenTwoEqualEitherThenComparingThemShouldReturnTrue() {
        Either<Exception, Integer> e1 = new Either<>(null, 1);
        Either<Exception, Integer> e2 = new Either<>(null, 1);
        Assertions.assertEquals(e1, e2);
    }

    @Test
    public void GivenAnEitherWithRightSideThenIsRightShouldReturnTrue() {
        Either<Exception, Integer> e1 = new Either<>(null, 1);
        Assertions.assertTrue(e1.isRight());
    }

    @Test
    public void GivenAnEitherWithLeftSideThenIsRightShouldReturnFalse() {
        Either<Exception, Integer> e1 = new Either<>(new Exception(""), null);
        Assertions.assertFalse(e1.isRight());
    }

    @Test
    public void GivenAnEitherWithRightSideThenMapShouldApply() {
        Either<Exception, Integer> e1 = new Either<>(null, 1);
        Either<Exception, String> expected = new Either<>(null, "1");
        Assertions.assertEquals(expected, e1.map(Object::toString));
    }

    @Test
    public void GivenAnEitherWithLeftSideThenMapShouldNotApply() {
        Either<Exception, Integer> e1 = new Either<>(new Exception(""), null);
        Assertions.assertEquals(e1, e1.map(Object::toString));
    }

    @Test
    public void GivenAnEitherWithRightSideThenFoldShouldApplyRightFunction() {
        Either<Exception, Integer> e1 = new Either<>(null, 1);
        Assertions.assertEquals("1", e1.fold(Throwable::getMessage, Object::toString));
    }

    @Test
    public void GivenAnEitherWithLeftSideThenFoldShouldApplyLeftFunction() {
        Either<Exception, Integer> e1 = new Either<>(new Exception("error"), null);
        Assertions.assertEquals("error", e1.fold(Throwable::getMessage, Object::toString));
    }
}

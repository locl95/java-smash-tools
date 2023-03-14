package org.kos.db.sql;

import org.kos.util.either.Either;
import org.kos.util.either.Left;
import org.kos.util.either.Right;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Sql {

    public <A> Either<Exception, List<A>> select(Connection conn, String sql, Function<ResultSet, Either<Exception, A>> reader) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            List<A> res = new ArrayList<>(List.of());
            while (rs.next()) {
                Either<Exception, A> foo = reader.apply(rs);
                if (foo.isRight()) res.add(foo.right());
                else return new Left<>(foo.left());
            }
            return new Right<>(res);
        } catch (SQLException e) {
            return new Left<>(e);
        }
    }

    public void update(Connection conn, String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

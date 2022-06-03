package pl.distributed.library.zmodel;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getLong("author_id"));
        author.setForename(resultSet.getString("forename"));
        author.setSurname(resultSet.getString("surname"));
        return author;
    }
}

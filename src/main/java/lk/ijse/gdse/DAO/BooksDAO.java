package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.Books;

import java.sql.SQLException;

public interface BooksDAO extends CrudDAO<Books>{
    public boolean UpdateBooks(int id) throws SQLException;
}

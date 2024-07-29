package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.BooksDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.Books;
import lk.ijse.gdse.Entity.Members;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAOImpl implements BooksDAO {
    @Override
    public boolean save(Books books) throws SQLException {
        return SQLUtil.execute("INSERT INTO books VALUES(?,?,?,?,?,?,?)",books.getBook_id(),books.getTitle(),books.getEdition(),books.getLanguage(),books.getAvailable_copies(),books.getShelf_location(),books.getCategory_id());
    }

    @Override
    public ArrayList<Books> getAll() throws SQLException {
        ArrayList<Books> books = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM books");
        while (rst.next()){
            Books books1 = new Books(
                    rst.getInt("book_id"),
                    rst.getString("title"),
                    rst.getString("edition"),
                    rst.getString("language"),
                    rst.getInt("available_copies"),
                    rst.getString("shelf_location"),
                    rst.getInt("category_id")
            );
            books.add(books1);
        }
        return books;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return SQLUtil.execute("DELETE FROM books WHERE book_id = ?",id);
    }

    @Override
    public boolean update(Books books) throws SQLException {
        return SQLUtil.execute("UPDATE books SET title = ? , edition = ? , language = ? , available_copies = ?, shelf_location = ?, category_id = ? WHERE book_id = ?",
                books.getTitle(),
                books.getEdition(),
                books.getLanguage(),
                books.getAvailable_copies(),
                books.getShelf_location(),
                books.getCategory_id()
                )
                ;
    }

    @Override
    public Books searchByTel(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM books WHERE book_id = ?",id +"");
        rst.next();
        return new Books(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getInt(7));

    }

    @Override
    public List<String> getTel() throws SQLException {
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT book_id FROM books")) {
            while (rst.next()) {
                String tel = rst.getString("book_id");
                idList.add(tel);
            }
        }
        return idList;
    }

    @Override
    public int getCurrentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT book_id FROM books ORDER BY book_id DESC LIMIT 1");
        if(rst.next()) {
            int bookId = rst.getInt(1);
            return bookId;
        }
        return 0;
    }
}

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
                books.getCategory_id(),
                books.getBook_id()
        )
                ;
    }

    @Override
    public Books searchByTel(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM books WHERE book_id = ?",id);
        if (rst.next()){
            int book_id = rst.getInt("book_id");
            String title = rst.getString("title");
            String edition = rst.getString("edition");
            String language = rst.getString("language");
            int available_copies = rst.getInt("available_copies");
            String shelf_location = rst.getString("shelf_location");
            int category_id = rst.getInt("category_id");
            return new Books(book_id,title,edition,language,available_copies,shelf_location,category_id);

        }
        return null;
    }

    @Override
    public List<String> getTel() throws SQLException {
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT book_id FROM books")) {
            while (rst.next()) {
                String tel = rst.getString("book_id");
                idList.add(String.valueOf(Integer.valueOf(tel)));
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

    @Override
    public boolean UpdateBooks(int id) throws SQLException {
        return SQLUtil.execute("UPDATE books\n" +
                "SET available_copies = available_copies - 1\n" +
                "WHERE book_id = ? AND available_copies > 0;\n",id);
    }
}

package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.BookCategoryDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.BookCategories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDAOImpl implements BookCategoryDAO {
    @Override
    public boolean save(BookCategories bookCategory) throws SQLException {
        return SQLUtil.execute("INSERT INTO book_categories VALUES(?,?)",bookCategory.getCategory_id(),bookCategory.getCategory_name());
    }

    @Override
    public ArrayList<BookCategories> getAll() throws SQLException {
        ArrayList<BookCategories> bookCategories = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM book_categories");
        while (rst.next()){
            BookCategories bookCategory = new BookCategories(
                    rst.getInt("category_id"),
                    rst.getString("category_name")
            );
            bookCategories.add(bookCategory);
        }
        return bookCategories;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return SQLUtil.execute("DELETE FROM book_categories WHERE category_id = ?",id);
    }

    @Override
    public boolean update(BookCategories bookCategories) throws SQLException {
        return SQLUtil.execute("UPDATE book_categories SET category_name = ?",
                bookCategories.getCategory_id(),
                bookCategories.getCategory_name())
                ;
    }

    @Override
    public BookCategories searchByTel(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM book_categories WHERE category_id = ?",id +"");
        rst.next();
        return new BookCategories(rst.getInt(1),rst.getString(2));
    }

    @Override
    public List<String> getTel() throws SQLException {
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT category_id FROM book_categories")) {
            while (rst.next()) {
                String id = rst.getString("category_id");
                idList.add(id);
            }
        }
        return idList;
    }

    @Override
    public int getCurrentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT category_id FROM book_categories ORDER BY category_id DESC LIMIT 1");
        if(rst.next()) {
            int categoryId = rst.getInt(1);
            return categoryId;
        }
        return 0;
    }
}

package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.BookCategoriesDTO;
import lk.ijse.gdse.Entity.BookCategories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookCategoriesBO extends SuperBO{
    public boolean saveBookCategories(BookCategoriesDTO books) throws SQLException;

    public ArrayList<BookCategoriesDTO> getAllBookCategories() throws SQLException;

    public boolean deleteBookCategories(int id) throws SQLException;

    public boolean updateBookCategories(BookCategoriesDTO bookCategoriesDTO) throws SQLException;

    public BookCategories searchById(String id) throws SQLException;

    public List<String> getId() throws SQLException;

    public int getCurrentId() throws SQLException;
}

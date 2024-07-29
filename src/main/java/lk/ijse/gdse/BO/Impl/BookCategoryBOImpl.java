package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.BookCategoriesBO;
import lk.ijse.gdse.DAO.BookCategoryDAO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DTO.BookCategoriesDTO;
import lk.ijse.gdse.Entity.BookCategories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryBOImpl implements BookCategoriesBO {
    BookCategoryDAO bookCategoryDAO = (BookCategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK_CATEGORIES);
    @Override
    public boolean saveBookCategories(BookCategoriesDTO bookCategoriesDTO) throws SQLException {
        return bookCategoryDAO.save(new BookCategories(bookCategoriesDTO.getCategory_id(), bookCategoriesDTO.getCategory_name()));
    }

    @Override
    public ArrayList<BookCategoriesDTO> getAllBookCategories() throws SQLException {
        ArrayList<BookCategoriesDTO> allBooksCategories= new ArrayList<>();
        ArrayList<BookCategories> all = bookCategoryDAO.getAll();
        for (BookCategories bc : all) {
            allBooksCategories.add(new BookCategoriesDTO(
                    bc.getCategory_id(),
                    bc.getCategory_name()
            ));
        }
        return allBooksCategories;
    }

    @Override
    public boolean deleteBookCategories(int id) throws SQLException {
        return bookCategoryDAO.delete(id);
    }

    @Override
    public boolean updateBookCategories(BookCategoriesDTO bookCategoriesDTO) throws SQLException {
        return bookCategoryDAO.update(new BookCategories(bookCategoriesDTO.getCategory_id(),bookCategoriesDTO.getCategory_name()));
    }

    @Override
    public BookCategories searchById(String id) throws SQLException {
        return bookCategoryDAO.searchByTel(id);
    }

    @Override
    public List<String> getId() throws SQLException {
        return bookCategoryDAO.getTel();
    }

    @Override
    public int getCurrentId() throws SQLException {
        return bookCategoryDAO.getCurrentId();
    }
}

package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.BooksBO;
import lk.ijse.gdse.DAO.BooksDAO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DTO.BooksDTO;
import lk.ijse.gdse.Entity.Books;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksBOImpl implements BooksBO {
    BooksDAO booksDAO = (BooksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS);
    @Override
    public boolean saveBooks(BooksDTO books) throws SQLException {
        return booksDAO.save(new Books(books.getBook_id(), books.getTitle(), books.getEdition(),books.getLanguage(), books.getAvailable_copies(),books.getShelf_location(),books.getCategory_id()));
    }

    @Override
    public ArrayList<BooksDTO> getAllBooks() throws SQLException {
        ArrayList<BooksDTO> allBooks= new ArrayList<>();
        ArrayList<Books> all = booksDAO.getAll();
        for (Books b : all) {
            allBooks.add(new BooksDTO(
                    b.getBook_id(),
                    b.getTitle(),
                    b.getEdition(),
                    b.getLanguage(),
                    b.getAvailable_copies(),
                    b.getShelf_location(),
                    b.getCategory_id()
                    ));
        }
        return allBooks;
    }

    @Override
    public boolean deleteBooks(int id) throws SQLException {
        return booksDAO.delete(id);
    }

    @Override
    public boolean updateBooks(BooksDTO books) throws SQLException {
        return booksDAO.update(new Books(books.getBook_id(),books.getTitle(),books.getEdition(), books.getLanguage(), books.getAvailable_copies(), books.getShelf_location(), books.getCategory_id()));
    }

    @Override
    public Books searchById(String id) throws SQLException {
        return booksDAO.searchByTel(id);
    }

    @Override
    public List<String> getId() throws SQLException {
        return booksDAO.getTel();
    }

    @Override
    public int getCurrentId() throws SQLException {
        return booksDAO.getCurrentId();
    }
}


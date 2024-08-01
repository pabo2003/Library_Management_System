package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.BooksDTO;
import lk.ijse.gdse.DTO.MembersDTO;
import lk.ijse.gdse.Entity.Books;
import lk.ijse.gdse.Entity.Members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BooksBO extends SuperBO{
    public boolean saveBooks(BooksDTO books) throws SQLException;

    public ArrayList<BooksDTO> getAllBooks() throws SQLException;

    public boolean deleteBooks(int id) throws SQLException;

    public boolean updateBooks(BooksDTO books) throws SQLException;

    public Books searchById(String id) throws SQLException;

    public List<String> getId() throws SQLException;

    public int getCurrentId() throws SQLException;
    public boolean UpdateBooks(int id) throws SQLException;
}

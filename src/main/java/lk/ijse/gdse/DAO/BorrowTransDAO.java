package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.BorrowingTransactions;
import lk.ijse.gdse.Entity.Members;

import java.sql.SQLException;
import java.util.ArrayList;


public interface BorrowTransDAO {
    public boolean save(BorrowingTransactions borrowingTransactions) throws SQLException;
    public ArrayList<BorrowingTransactions> getAll();
    public int getCurrentId() throws SQLException;

}
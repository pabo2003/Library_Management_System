package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.BorrowingTransactionsDTO;
import lk.ijse.gdse.Entity.BorrowingTransactions;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BarrowTransBO {
    public boolean save(BorrowingTransactionsDTO borrowingTransactionsDTO) throws SQLException;
    public ArrayList<BorrowingTransactionsDTO> getAll();
    public int getCurrentId() throws SQLException;
}
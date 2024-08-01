package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.BarrowTransBO;
import lk.ijse.gdse.DAO.BorrowTransDAO;
import lk.ijse.gdse.DAO.Impl.BorrowTransDAOImpl;
import lk.ijse.gdse.DTO.BorrowingTransactionsDTO;
import lk.ijse.gdse.Entity.BorrowingTransactions;

import java.sql.SQLException;
import java.util.ArrayList;

public class BarrowTransBOImpl implements BarrowTransBO {
//Avggytt
    BorrowTransDAO borrowTransDAO = new BorrowTransDAOImpl();

    @Override
    public boolean save(BorrowingTransactionsDTO borrowingTransactionsDTO) throws SQLException {
        return borrowTransDAO.save(new BorrowingTransactions(
                borrowingTransactionsDTO.getTransaction_id(),
                borrowingTransactionsDTO.getBook_id(),
                borrowingTransactionsDTO.getMember_id(),
                borrowingTransactionsDTO.getBorrow_date(),
                borrowingTransactionsDTO.getDue_date()));
    }

    @Override
    public ArrayList<BorrowingTransactionsDTO> getAll() {
        return null;
    }

    @Override
    public int getCurrentId() throws SQLException {
        return borrowTransDAO.getCurrentId();
    }
}
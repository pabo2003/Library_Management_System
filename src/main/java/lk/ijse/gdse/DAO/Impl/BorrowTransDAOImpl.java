package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.BorrowTransDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.BorrowingTransactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowTransDAOImpl implements BorrowTransDAO {
    @Override
    public boolean save(BorrowingTransactions borrowingTransactions) throws SQLException, SQLException {
        return SQLUtil.execute("INSERT INTO borrowing_transactions (transaction_id,book_id, member_id, borrow_date, due_date) VALUES (?,?, ?, ?, ?)",
                borrowingTransactions.getTransaction_id(),
                borrowingTransactions.getBook_id(),
                borrowingTransactions.getMember_id(),
                borrowingTransactions.getBorrow_date(),
                borrowingTransactions.getDue_date());
    }

    @Override
    public ArrayList<BorrowingTransactions> getAll() {
        return null;
    }

    @Override
    public int getCurrentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT transaction_id FROM borrowing_transactions ORDER BY transaction_id DESC LIMIT 1");
        if(rst.next()) {
            int borrowId = rst.getInt(1);
            return borrowId;
        }
        return 0;    }
}

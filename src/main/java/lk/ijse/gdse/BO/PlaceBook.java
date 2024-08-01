package lk.ijse.gdse.BO;

import lk.ijse.gdse.BO.Impl.BarrowTransBOImpl;
import lk.ijse.gdse.BO.Impl.BooksBOImpl;
import lk.ijse.gdse.BO.Impl.MemberBOImpl;
import lk.ijse.gdse.DB.DBConnection;
import lk.ijse.gdse.DTO.BorrowingTransactionsDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceBook {
    public static boolean placeBookBorrow(BorrowingTransactionsDTO PB) throws SQLException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        BarrowTransBO borrowTransBO = new BarrowTransBOImpl();
        MemberBO memberBO = new MemberBOImpl();
        BooksBO booksBO = new BooksBOImpl();

        try {
            boolean isBorrowUpdate = borrowTransBO.save(PB);
            if (isBorrowUpdate) {
                boolean QtyUpdate = booksBO.UpdateBooks(PB.getBook_id());
                if (QtyUpdate) {
                    connection.commit();
                    return true;
                }

            }

            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

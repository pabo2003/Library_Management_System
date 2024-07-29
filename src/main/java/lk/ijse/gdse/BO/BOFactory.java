package lk.ijse.gdse.BO;

import lk.ijse.gdse.BO.Impl.*;
import lk.ijse.gdse.DAO.Impl.*;
import lk.ijse.gdse.Entity.BorrowingTransactions;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOType{
        MEMBER,BOOKS,BOOK_CATEGORIES,BORROWING_TRANSACTIONS
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case MEMBER:
                return new MemberBOImpl();
            case BOOKS:
                return new BooksBOImpl();
            case BOOK_CATEGORIES:
                return new BookCategoryBOImpl();
            /*case BORROWING_TRANSACTIONS:
                return new BorrowingTransactionsBOImpl();*/
            default:
                return null;
        }
    }
}

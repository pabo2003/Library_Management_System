package lk.ijse.gdse.DAO;

import lk.ijse.gdse.DAO.Impl.*;

public class DAOFactory implements SuperDAO {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        MEMBER,BOOKS,BOOK_CATEGORIES,BORROWING_TRANSACTIONS
    }
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case MEMBER:
                return new MemberDAOImpl();
            case BOOKS:
                return new BooksDAOImpl();
            /*case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_DETAILS:
                return new SupplierDetailsDAOImpl();*/
            default:
                return null;
        }
    }

}

package lk.ijse.hibernate.repository;

import lk.ijse.hibernate.repository.cusom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }


    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PARENT:
                return new ParentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PARENT, PROGRAM,PAYMENT,LOGIN
    }
}


package lk.ijse.hibernate.business;

import lk.ijse.hibernate.business.custom.SearchListFormBO;
import lk.ijse.hibernate.business.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBOFactory(){
        if (boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentRegistrationFormBOImpl();
            case PARENT:
                return new ParentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case SEARCH:return new SearchListFormBOImpl();
            default:
                return null;

        }
    }

    public enum BOTypes{
        STUDENT,PARENT,PAYMENT,PROGRAM,LOGIN,SEARCH
    }
}

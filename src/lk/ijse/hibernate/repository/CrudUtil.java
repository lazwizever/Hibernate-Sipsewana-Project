package lk.ijse.hibernate.repository;

import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.management.Query;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static Query getQuery(String hql,Object...args){
        Session session = FactoryConfiguration.getInstance().openSession();
        org.hibernate.query.Query query = session.createQuery(hql);
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i+1,args[i]);
        }
        return (Query) query;
    }

    public static boolean executeUpdate(String hql,Object... args){
        return executeUpdate(hql, args);
    }

   /* public static ResultSet executeQuery(String hql, Object... args) throws SQLException, ClassNotFoundException {
        return executeQuery(hql, args);
    }*/


}

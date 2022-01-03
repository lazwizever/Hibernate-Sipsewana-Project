package lk.ijse.hibernate.repository.cusom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Parent;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.cusom.ParentDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.List;

public class ParentDAOImpl implements ParentDAO {

    @Override
    public boolean add(Parent parent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(parent);

        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean update(Parent parent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(parent);

        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public ObservableList<Parent> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery("SELECT pId FROM Parent ORDER BY pId DESC LIMIT 1");
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list.size() == 0 ? null : list.get(0);
    }
}

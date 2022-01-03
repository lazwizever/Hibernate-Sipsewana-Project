package lk.ijse.hibernate.repository.cusom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.LoginMaster;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.cusom.LoginDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean add(LoginMaster login) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(LoginMaster login) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ObservableList<LoginMaster> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM LoginMaster";
        Query query = session.createQuery(hql);

        List<LoginMaster> list = query.list();

        ObservableList<LoginMaster> loginMasters = FXCollections.observableArrayList();
        loginMasters.addAll(list);

        transaction.commit();
        session.close();
        return loginMasters;
    }
}

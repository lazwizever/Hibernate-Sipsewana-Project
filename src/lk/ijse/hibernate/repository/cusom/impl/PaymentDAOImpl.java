package lk.ijse.hibernate.repository.cusom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.StudentProgram;
import lk.ijse.hibernate.repository.cusom.PaymentDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean add(StudentProgram studentProgram) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentProgram);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(StudentProgram studentProgram) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ObservableList<StudentProgram> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM StudentProgram";
        Query query = session.createQuery(hql);

        List<StudentProgram> list = query.list();

        ObservableList<StudentProgram> studentProgramObList = FXCollections.observableArrayList();
        studentProgramObList.addAll(list);

        transaction.commit();
        session.close();
        return studentProgramObList;
    }

    @Override
    public ObservableList<StudentProgram> getPaymentDetails(String studentId) {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM StudentProgram WHERE student = :sId";
        Query query = session.createQuery(hql);

        query.setParameter("sId",studentId);
        List<StudentProgram> list = query.list();

        ObservableList<StudentProgram> studentProgramObList = FXCollections.observableArrayList();
        studentProgramObList.addAll(list);

        transaction.commit();
        session.close();
        return studentProgramObList;
    }
}

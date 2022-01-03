package lk.ijse.hibernate.repository.cusom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.cusom.StudentDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();

        ObservableList<Student> studentDetails = FXCollections.observableArrayList();
        studentDetails.addAll(list);

        transaction.commit();
        session.close();
        return studentDetails;
    }

    @Override
    public ObservableList<Student> searchStudent(String name) {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Student s WHERE s.stName LIKE :stdentName";
        Query query = session.createQuery(hql);
        query.setParameter("stdentName","%"+name+"%");

        List<Student> list = query.list();

        ObservableList<Student> studentDetails = FXCollections.observableArrayList();
        studentDetails.addAll(list);

        transaction.commit();
        session.close();
        return studentDetails;
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query = session.createSQLQuery("SELECT stRegistrationNo FROM Student ORDER BY stRegistrationNO DESC LIMIT 1");
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list.size() == 0 ? null : list.get(0);
    }
}

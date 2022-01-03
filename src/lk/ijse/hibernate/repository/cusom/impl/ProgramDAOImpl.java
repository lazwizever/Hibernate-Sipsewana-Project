package lk.ijse.hibernate.repository.cusom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public boolean add(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Program";
        Query query = session.createQuery(hql);

        List<Program> list = query.list();

       ObservableList<Program> observableList = FXCollections.observableArrayList();
       observableList.addAll(list);

       transaction.commit();
       session.close();
       return observableList;

    }


    public String getProgramId(String programType){
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        /*String pType = programType;*/


        String hql = "FROM Program WHERE programName = :program";
        Query query = session.createQuery(hql);
        query.setParameter("program",programType);
        List<Program> list = query.list();

        String programId = null;

        for (Program program : list) {
            programId = program.getProgramId();
        }

        transaction.commit();
        session.close();
        return programId;
    }

    @Override
    public ObservableList<Program> getProgramForId(String programIds) {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Program WHERE programId = :programId";
        Query query = session.createQuery(hql);
        query.setParameter("programId",programIds);

        List<Program> list = query.list();

        ObservableList<Program> programs = FXCollections.observableArrayList();
        programs.addAll(list);

        transaction.commit();
        session.close();
        return programs;

    }

    @Override
    public ObservableList<Program> getProgramForProgramType(String programType) {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Program WHERE programName = :program";
        Query query = session.createQuery(hql);
        query.setParameter("program",programType);
        List<Program> list = query.list();

        ObservableList<Program> programObList = FXCollections.observableArrayList();
        programObList.addAll(list);

        transaction.commit();
        session.close();
        return programObList;
    }
}

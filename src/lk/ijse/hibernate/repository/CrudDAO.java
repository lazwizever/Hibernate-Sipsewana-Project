package lk.ijse.hibernate.repository;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Student;

import java.sql.SQLException;

public interface CrudDAO<T,ID> extends SuperDAO{
    boolean add(T t) throws SQLException, ClassNotFoundException;

    boolean update(T t) throws SQLException, ClassNotFoundException;

    ObservableList<T> getAll() throws SQLException, ClassNotFoundException;
}

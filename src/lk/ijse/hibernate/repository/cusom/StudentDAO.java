package lk.ijse.hibernate.repository.cusom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.CrudDAO;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student,String> {
    ObservableList<Student> searchStudent(String name);

    String getLastId();


}

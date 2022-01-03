package lk.ijse.hibernate.repository.cusom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.StudentProgram;
import lk.ijse.hibernate.repository.CrudDAO;

public interface PaymentDAO extends CrudDAO<StudentProgram,String> {
    ObservableList<StudentProgram> getPaymentDetails(String studentId);
}

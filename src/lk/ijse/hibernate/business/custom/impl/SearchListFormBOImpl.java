package lk.ijse.hibernate.business.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.custom.SearchListFormBO;
import lk.ijse.hibernate.dto.ParentDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.StudentDAO;

import java.sql.SQLException;

public class SearchListFormBOImpl implements SearchListFormBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public ObservableList<StudentDTO> getAllStudentDetails() throws SQLException, ClassNotFoundException {
        ObservableList<Student> studentDetails = studentDAO.getAll();
        ObservableList<StudentDTO> studentDetailsDTO = FXCollections.observableArrayList();

        for (Student temp : studentDetails) {
            ParentDTO parentDTO = new ParentDTO(
                    temp.getParent().getpId(),temp.getParent().getPstRegistrationNO(),temp.getParent().getpName(),temp.getParent().getpAddress(),temp.getParent().getNIC(),temp.getParent().getpJob(),
                    temp.getParent().getpTitle(),temp.getParent().getGender(),temp.getParent().getpContactNO()
            );

            StudentDTO studentDTO = new StudentDTO(
                    temp.getStRegistrationNO(), temp.getStName(), temp.getStAddress(),temp.getSstRegistrationDate(),temp.geteMail(),temp.getNic(),
                    temp.getContactNO(), temp.getStGender(), temp.getStDOB(),parentDTO
            );

            studentDetailsDTO.add(studentDTO);
        }
        return studentDetailsDTO;
    }

    @Override
    public ObservableList<StudentDTO> getStudentForName(String name) {
        ObservableList<Student> students = studentDAO.searchStudent(name);
        ObservableList<StudentDTO> studentDTOS = FXCollections.observableArrayList();

        for (Student temp : students) {

            ParentDTO parentDTO = new ParentDTO(
                    temp.getParent().getpId(), temp.getParent().getPstRegistrationNO(), temp.getParent().getpName(),temp.getParent().getpAddress(),temp.getParent().getNIC(),temp.getParent().getpJob(),temp.getParent().getpTitle(),temp.getParent().getGender(),temp.getParent().getpContactNO()
            );

            StudentDTO studentDTO = new StudentDTO(
                    temp.getStRegistrationNO(), temp.getStName(), temp.getStAddress(), temp.getSstRegistrationDate(), temp.geteMail(),  temp.getNic(), temp.getContactNO(),temp.getStGender(),temp.getStDOB(),parentDTO
            );
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }
}

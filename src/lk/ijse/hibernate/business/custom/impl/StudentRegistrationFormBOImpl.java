package lk.ijse.hibernate.business.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.custom.StudentRegistrationBO;
import lk.ijse.hibernate.dto.ParentDTO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;
import lk.ijse.hibernate.entity.Parent;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.StudentProgram;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.ParentDAO;
import lk.ijse.hibernate.repository.cusom.PaymentDAO;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;
import lk.ijse.hibernate.repository.cusom.StudentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationFormBOImpl implements StudentRegistrationBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    ParentDAO parentDAO = (ParentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PARENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
   PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ObservableList<StudentProgramDTO> getStudentProgramDetails() throws SQLException, ClassNotFoundException {
        ObservableList<StudentProgram> studentPrograms = paymentDAO.getAll();

        ObservableList<StudentProgramDTO> studentProgramsObList = FXCollections.observableArrayList();

        for (StudentProgram temp : studentPrograms) {

            StudentProgramDTO studentProgramDTO = new StudentProgramDTO();
            studentProgramDTO.setProgramId(temp.getProgram().getProgramId());
            studentProgramDTO.setStRegistrationNo(temp.getStudent().getStRegistrationNO());
            studentProgramDTO.setStRegistrationDate(temp.getStRegistrationDate());

            studentProgramsObList.add(studentProgramDTO);
        }
        return studentProgramsObList;
    }

    @Override
    public boolean saveStudentAndGuardian(ParentDTO parentDTO) throws SQLException, ClassNotFoundException {

        StudentDTO studentDTO = parentDTO.getStudentList().get(0);

        Parent parent = new Parent(
                parentDTO.getpId(),parentDTO.getStRegistrationNO(),parentDTO.getpName(),parentDTO.getpAddress(),parentDTO.getpNIC(),
                parentDTO.getpJob(),parentDTO.getpTitle(),parentDTO.getpGender(),parentDTO.getpContactNO()
        );

        Student student = new Student(studentDTO.getStRegistrationNo(),
                studentDTO.getStName(),studentDTO.getStRegistrationDate(),studentDTO.getStAddress(),
                studentDTO.getStNIC(),studentDTO.getStContactNo(),studentDTO.getStEmail(),studentDTO.getGender(),studentDTO.getDOB(),parent
                );

        List<Student> studentList = new ArrayList<>();

        studentList.add(student);
        parent.setStudentList(studentList);
        return parentDAO.add(parent);
    }

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
    public boolean updateStudentAndGuardian(ParentDTO parentDTO) throws SQLException, ClassNotFoundException {

        StudentDTO studentDTO = parentDTO.getStudentList().get(0);

        Parent parent = new Parent(
                parentDTO.getpId(),parentDTO.getStRegistrationNO(),parentDTO.getpName(),parentDTO.getpAddress(),parentDTO.getpNIC(),
                parentDTO.getpJob(),parentDTO.getpTitle(),parentDTO.getpGender(),parentDTO.getpContactNO()
        );

        Student student = new Student(studentDTO.getStRegistrationNo(),
                studentDTO.getStName(),studentDTO.getStRegistrationDate(),studentDTO.getStAddress(),
                studentDTO.getStNIC(),studentDTO.getStContactNo(),studentDTO.getStEmail(),studentDTO.getGender(),studentDTO.getDOB(),parent
        );

        List<Student> studentList = new ArrayList<>();

        studentList.add(student);
        parent.setStudentList(studentList);

        return parentDAO.update(parent);
    }

    @Override
    public ObservableList<StudentDTO> getStudentForName(String name) throws SQLException, ClassNotFoundException {
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

    @Override
    public String generateStudentId() {
        String lastId = studentDAO.getLastId();

        if (lastId != null) {
            int index = Integer.parseInt(lastId.split("-")[1]);
            index++;
            return (index < 10) ? "S-000" + index : (index < 100) ? "S-00" + index : (index < 1000) ? "S-0" + index : "S-" + index;

        } else {
            return "S-0001";
        }
    }

    @Override
    public String generateParentId() {
        String lastId = parentDAO.getLastId();

        if (lastId != null) {
            int index = Integer.parseInt(lastId.split("-")[1]);
            index++;
            return (index < 10) ? "P-000" + index : (index < 100) ? "P-00" + index : (index < 1000) ? "P-0" + index : "P-" + index;

        } else {
            return "P-0001";
        }
    }

    @Override
    public ObservableList<ProgramDTO> getProgramDetailsFromProgramType(String programType) {
        ObservableList<Program> programForProgramType = programDAO.getProgramForProgramType(programType);
        ObservableList<ProgramDTO> programDTOList = FXCollections.observableArrayList();

        for (Program temp : programForProgramType) {
            ProgramDTO programDTO = new ProgramDTO(
                    temp.getProgramId(), temp.getProgramName(), temp.getDuration(), temp.getProgramFee()
            );
            programDTOList.add(programDTO);
        }
        return programDTOList;
    }

    @Override
    public ObservableList<ProgramDTO> getProgramsForId(String programId) {
        ObservableList<Program> programForId = programDAO.getProgramForId(programId);

        ObservableList<ProgramDTO> programDTOObList = FXCollections.observableArrayList();

        for (Program temp: programForId) {
            ProgramDTO programDTO = new ProgramDTO(
                    temp.getProgramId(),temp.getProgramName(), temp.getDuration(),temp.getProgramFee()
            );

            programDTOObList.add(programDTO);
        }
        return programDTOObList;
    }


}

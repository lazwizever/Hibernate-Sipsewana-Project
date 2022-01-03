package lk.ijse.hibernate.business.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.custom.PaymentBO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.dto.StudentProgramDTO;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.entity.StudentProgram;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.PaymentDAO;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean makePayment(StudentProgramDTO studentProgramDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, studentProgramDTO.getStRegistrationNo());
        Program program = session.get(Program.class, studentProgramDTO.getProgramId());

        StudentProgram studentProgram = new StudentProgram();
        studentProgram.setProgram(program);
        studentProgram.setStudent(student);
        studentProgram.setStRegistrationDate(studentProgramDTO.getStRegistrationDate());

        transaction.commit();
        session.close();
        return paymentDAO.add(studentProgram);
    }

    @Override
    public ObservableList<StudentProgramDTO> getPaymentDetails(String stRegistrationNo) throws SQLException, ClassNotFoundException {
        ObservableList<StudentProgram> paymentDetails = paymentDAO.getPaymentDetails(stRegistrationNo);

        ObservableList<StudentProgramDTO> paymentDetailsObList= FXCollections.observableArrayList();

        for (StudentProgram temp : paymentDetails) {

            StudentProgramDTO studentProgramDTO = new StudentProgramDTO();
            studentProgramDTO.setProgramId(temp.getProgram().getProgramId());
            studentProgramDTO.setStRegistrationNo(temp.getStudent().getStRegistrationNO());
            studentProgramDTO.setStRegistrationDate(temp.getStRegistrationDate());

            paymentDetailsObList.add(studentProgramDTO);
        }

        return paymentDetailsObList;
    }

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
    public ObservableList<ProgramDTO> getAllPrograms() throws SQLException, ClassNotFoundException {
        ObservableList<Program> programDetails = programDAO.getAll();
        ObservableList<ProgramDTO> programs = FXCollections.observableArrayList();

        for (Program tempProgramDetail : programDetails) {

            ProgramDTO programDTO = new ProgramDTO(
                    tempProgramDetail.getProgramId(),tempProgramDetail.getProgramName(),
                    tempProgramDetail.getDuration(),tempProgramDetail.getProgramFee()
            );

            programs.add(programDTO);
        }
        return programs;

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
    public String getProgramId(String id) {
        return programDAO.getProgramId(id);
    }
}

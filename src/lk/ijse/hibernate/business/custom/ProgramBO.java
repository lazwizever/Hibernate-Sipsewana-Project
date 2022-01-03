package lk.ijse.hibernate.business.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.ProgramDTO;

import java.sql.SQLException;

public interface ProgramBO extends SuperBO {
    public boolean addProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    public ObservableList<ProgramDTO> getAllProgramDetails() throws SQLException, ClassNotFoundException;

    ObservableList<ProgramDTO> getProgramsForId(String programId);

    ObservableList<ProgramDTO> getProgramDetailsFromProgramType(String programType);

    boolean updatePrograms(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

}

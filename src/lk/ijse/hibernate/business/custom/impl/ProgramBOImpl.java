package lk.ijse.hibernate.business.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.business.custom.ProgramBO;
import lk.ijse.hibernate.dto.ProgramDTO;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.repository.DAOFactory;
import lk.ijse.hibernate.repository.cusom.ProgramDAO;
import lk.ijse.hibernate.repository.cusom.StudentDAO;

import java.sql.SQLException;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean addProgram(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        Program program = new Program(
                programDTO.getId(),programDTO.getName(),programDTO.getFee(),programDTO.getDuration()
        );
        programDAO.add(program);
        return true;
    }

    @Override
    public ObservableList<ProgramDTO> getAllProgramDetails() throws SQLException, ClassNotFoundException {
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
    public boolean updatePrograms(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        Program program = new Program(
                programDTO.getId(),programDTO.getName(),programDTO.getFee(),programDTO.getDuration()
        );
        programDAO.update(program);
        return true;
    }


}

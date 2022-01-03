package lk.ijse.hibernate.repository.cusom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.entity.Program;
import lk.ijse.hibernate.repository.CrudDAO;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program,String> {

    String getProgramId(String programType);

    ObservableList<Program> getProgramForId(String ProgramId);

    ObservableList<Program> getProgramForProgramType(String programType);
}

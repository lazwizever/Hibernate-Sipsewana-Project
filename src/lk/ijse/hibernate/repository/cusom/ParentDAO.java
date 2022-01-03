package lk.ijse.hibernate.repository.cusom;

import lk.ijse.hibernate.entity.Parent;
import lk.ijse.hibernate.repository.CrudDAO;

public interface ParentDAO extends CrudDAO<Parent,String> {
    String getLastId();
}

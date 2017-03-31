package Qly_dn.repository;

import Qly_dn.model.UnitName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface UnitNameRepository extends CrudRepository<UnitName, Integer>{
    UnitName findByUnitName (String unitName);
    UnitName findById (int id);
}

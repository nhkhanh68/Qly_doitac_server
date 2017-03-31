package Qly_dn.repository;

import Qly_dn.model.TypeContract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface TypeContractRepository extends CrudRepository<TypeContract, Integer>{
    TypeContract findById (int id);
    TypeContract findByTypeContract (String string);
}

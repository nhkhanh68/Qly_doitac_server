package Qly_dn.repository;

import Qly_dn.model.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface ContractRepository extends CrudRepository<Contract, Integer>{
    List<Contract> findByTypeContractId(int id);
    List<Contract> findByUnitNameId(int id);
    Contract findById (int id);
}

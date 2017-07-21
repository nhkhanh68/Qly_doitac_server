package Qly_dn.repository;

import Qly_dn.model.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer>{
    Partner findById(int id);
    Partner findByPartnerNameContaining (String string);
}

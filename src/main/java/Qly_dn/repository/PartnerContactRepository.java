package Qly_dn.repository;

import Qly_dn.model.PartnerContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface PartnerContactRepository extends CrudRepository<PartnerContact, Integer>{
    PartnerContact findById (int id);
    PartnerContact findByIdAndPartnerId(int id, int partnerId);
}

package Qly_dn.repository;

import Qly_dn.model.PartnerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface PartnerInfoRepository extends CrudRepository<PartnerInfo, Integer>{
    PartnerInfo findByPartnerId(int id);
}

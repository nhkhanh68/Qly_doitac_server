package Qly_dn.repository;

import Qly_dn.model.CooperateActivity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nhkha on 13/04/2017.
 */
public interface CooperateActivityRepository extends CrudRepository<CooperateActivity, Integer> {
    CooperateActivity findById(int id);
}

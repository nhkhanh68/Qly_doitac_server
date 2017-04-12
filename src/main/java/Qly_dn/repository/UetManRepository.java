package Qly_dn.repository;

import Qly_dn.model.UetMan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface UetManRepository extends CrudRepository<UetMan, Integer>{
    UetMan findByUetManName (String uetManName);
    UetMan findByUetManNameContaining(String s);
    UetMan findById (int id);
}

package Qly_dn.repository;

import Qly_dn.model.Continent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface ContinentRepository extends CrudRepository<Continent, Integer>{
    Continent findByContinentName(String continentName);
}

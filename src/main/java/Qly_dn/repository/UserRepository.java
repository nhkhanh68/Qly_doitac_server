package Qly_dn.repository;

import Qly_dn.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nhkha on 25/03/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByToken(String token);
    User findByUserName(String userName);
}

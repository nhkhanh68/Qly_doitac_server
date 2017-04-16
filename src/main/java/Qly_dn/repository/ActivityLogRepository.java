package Qly_dn.repository;

import Qly_dn.model.ActivityLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by nhkha on 13/04/2017.
 */
public interface ActivityLogRepository extends CrudRepository<ActivityLog, Integer> {
    Set<ActivityLog> findAll();
}

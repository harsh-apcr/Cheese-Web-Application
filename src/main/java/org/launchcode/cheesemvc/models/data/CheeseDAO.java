package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository // declares spring that this interface is an actual repository which enables spring to create its instance
            // automatically using @Autowired
@Transactional  // Every method in the interface should be wrapped by a database transaction
public interface CheeseDAO extends CrudRepository<Cheese, Integer> {
}

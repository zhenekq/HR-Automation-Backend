package by.mifort.automation.hr.dev.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for working with candidate's attributes types in database
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see AttributeTypes
 */

@Repository
public interface AttributeTypesRepository extends JpaRepository<AttributeTypes, Integer> {
    /**
     * @param name of attribute
     * @return Paginated information about attribute types
     */
    List<AttributeTypes> findByName(String name);

    Boolean existsByName(String name);
}

package by.mifort.automation.hr.dev.type;

import java.util.List;

/**
 * Service layer for validation and business logic for AttributeTypesRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see AttributeTypesRepository
 */

public interface AttributeTypesService {

    /**
     * @return List of all exists attribute types
     */
    List<AttributeTypes> getAll();


    /**
     * Get attribute type by id
     *
     * @param id identifier of attribute type
     */
    AttributeTypes getById(Integer id);

    /**
     * Create new attribute type in database
     *
     * @param attributeTypes body of our attribute type
     */
    AttributeTypes create(AttributeTypes attributeTypes);

    /**
     * Update exists attribute type
     *
     * @param id             attribute's type identifier
     * @param attributeTypes body of attribute's type
     */
    AttributeTypes updateById(Integer id, AttributeTypes attributeTypes);

    /**
     * Archive exists attribute's type
     *
     * @param id attribute's type identifier
     */
    AttributeTypes archiveById(Integer id);

}

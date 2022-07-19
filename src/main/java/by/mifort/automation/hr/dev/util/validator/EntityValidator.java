package by.mifort.automation.hr.dev.util.validator;

/**
 * Assert differences between 2 entities
 * Used for Patch updating entities
 *
 * @author yauheni_vozny
 * @version 1.0
 */
@FunctionalInterface
public interface EntityValidator<T> {

    /**
     * Check is valid entity, means exists all need fields
     *
     * @param entity entity to check
     * @return boolean value valid or not
     */
    boolean isValidParams(T entity);

}

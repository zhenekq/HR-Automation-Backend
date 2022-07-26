package by.mifort.automation.hr.dev.shared.exception.storage;

public class AttributeTypeExceptionResponseStorage {
    private AttributeTypeExceptionResponseStorage() {
    }

    public static String attributeTypeNotFound(Integer attributeId) {
        return String.format("Attribute type with id '%d' not found!", attributeId);
    }

    public static String attributeTypeExists(String name) {
        return String.format("Attribute type with name '%s' already exists!", name);
    }
}

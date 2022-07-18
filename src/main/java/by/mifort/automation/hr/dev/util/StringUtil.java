package by.mifort.automation.hr.dev.util;

public class StringUtil {

    private StringUtil() {
    }

    public static String attributeTypeException(Integer id) {
        return String.format("Attribute type with id: %d not found!", id);
    }

    public static String candidateTypeException(String id) {
        return String.format("Candidate with id: %s not found!", id);
    }

}

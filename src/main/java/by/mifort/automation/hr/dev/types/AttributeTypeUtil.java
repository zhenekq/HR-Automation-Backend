package by.mifort.automation.hr.dev.types;

import java.util.List;

public class AttributeTypeUtil {

    private static final String DOB_LABEL = "Date of birth";
    private static final String DOB_FACEBOOK_ID = "Facebook";
    private static final String DOB_GITHUB_ID = "Github";
    private static final String DOB_PASSPORT = "Passport number";

    private AttributeTypeUtil() {
    }

    public static void addLabel(AttributeTypes attributeType, AttributeTypesDto dto) {
        switch (attributeType.getName()) {
            case "date_of_birth" -> dto.setLabel(DOB_LABEL);
            case "facebookid" -> dto.setLabel(DOB_FACEBOOK_ID);
            case "githubid" -> dto.setLabel(DOB_GITHUB_ID);
            case "passport_number" -> dto.setLabel(DOB_PASSPORT);
            default -> dto.setLabel(firstUpperCase(attributeType.getName()));
        }
    }

    public static void addLabelList(List<AttributeTypes> attributeTypes, List<AttributeTypesDto> dto) {
        for (int i = 0; i < attributeTypes.size(); i++) {
            AttributeTypes attributeType = attributeTypes.get(i);
            AttributeTypesDto dtoType = dto.get(i);
            addLabel(attributeType, dtoType);
        }
    }

    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

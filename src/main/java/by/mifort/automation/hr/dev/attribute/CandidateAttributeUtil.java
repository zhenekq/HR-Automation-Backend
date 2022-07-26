package by.mifort.automation.hr.dev.attribute;

import java.util.List;

public class CandidateAttributeUtil {

    private static final String DOB_LABEL = "Date of birth";
    private static final String DOB_FACEBOOK_ID = "Facebook";
    private static final String DOB_GITHUB_ID = "Github";
    private static final String DOB_PASSPORT = "Passport number";

    public static void addLabel(CandidateAttributes attributeType, CandidateAttributesDto dto) {
        switch (attributeType.getAttributeTypes().getName()) {
            case "date_of_birth" -> dto.setLabel(DOB_LABEL);
            case "facebookid" -> dto.setLabel(DOB_FACEBOOK_ID);
            case "githubid" -> dto.setLabel(DOB_GITHUB_ID);
            case "passport_number" -> dto.setLabel(DOB_PASSPORT);
            default -> dto.setLabel(firstUpperCase(attributeType.getAttributeTypes().getName()));
        }
    }

    public static void addLabelList(List<CandidateAttributes> attributeTypes, List<CandidateAttributesDto> dto) {
        for (int i = 0; i < attributeTypes.size(); i++) {
            CandidateAttributes attributeType = attributeTypes.get(i);
            CandidateAttributesDto dtoType = dto.get(i);
            addLabel(attributeType, dtoType);
        }
    }

    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}

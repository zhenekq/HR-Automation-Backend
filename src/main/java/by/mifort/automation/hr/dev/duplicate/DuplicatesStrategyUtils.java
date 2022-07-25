package by.mifort.automation.hr.dev.duplicate;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;

import java.util.List;

/**
 * Utils class for candidate's duplicates
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see DuplicatesStrategy
 */
public class DuplicatesStrategyUtils {

    private DuplicatesStrategyUtils() {
    }

    public static String setValues(List<CandidateAttributes> attributes, String type) {
        String res = "";
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).getAttributeTypes().getName().equals(type)) {
                res = attributes.get(i).getValue();
                break;
            }
        }
        return res;
    }
}

package by.mifort.automation.hr.dev.service.duplicates;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Utils class for candidate's duplicates
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see DuplicatesStrategy
 */
public class DuplicatesStrategyUtils {

    public static String setValues(List<CandidateAttributes> attributes, String type){
        String res = "";
        for(int i=0;i<attributes.size();i++){
            if(attributes.get(i).getAttributeTypes().getName().equals(type)){
                res = attributes.get(i).getValue();
                break;
            }
        }
        return res;
    }
}

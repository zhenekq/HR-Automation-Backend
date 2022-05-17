package by.mifort.automation.hr.dev.service.duplicates.comparator;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyUtils;

import java.util.Comparator;
import java.util.List;

public class FirstnameLastnameCityDateComparator implements Comparator<Candidate> {

    private static final String FIRSTNAME_IN_DB = "firstname";
    private static final String LASTNAME_IN_DB = "lastname";
    private static final String DATE_OF_BIRTH_IN_DB = "date_of_birth";
    private static final String CITY_IN_DB = "city";

    @Override
    public int compare(Candidate o1, Candidate o2) {
        List<CandidateAttributes> o1Attr = o1.getAttributes();
        List<CandidateAttributes> o2Attr = o2.getAttributes();

        String firstCandidateFirstname = DuplicatesStrategyUtils.setValues(o1Attr, FIRSTNAME_IN_DB);
        String firstCandidateLastname = DuplicatesStrategyUtils.setValues(o1Attr, LASTNAME_IN_DB);
        String firstCandidateDate = DuplicatesStrategyUtils.setValues(o1Attr, DATE_OF_BIRTH_IN_DB);
        String firstCandidateCity = DuplicatesStrategyUtils.setValues(o1Attr, CITY_IN_DB);

        String secondCandidateFirstname = DuplicatesStrategyUtils.setValues(o2Attr, FIRSTNAME_IN_DB);
        String secondCandidateLastname = DuplicatesStrategyUtils.setValues(o2Attr, LASTNAME_IN_DB);
        String secondCandidateDate = DuplicatesStrategyUtils.setValues(o2Attr, DATE_OF_BIRTH_IN_DB);
        String secondCandidateCity = DuplicatesStrategyUtils.setValues(o2Attr, CITY_IN_DB);

        boolean isFirstnameEquals = firstCandidateFirstname.equals(secondCandidateFirstname) &&
                !firstCandidateFirstname.isEmpty();
        boolean isLastnameEquals = firstCandidateLastname.equals(secondCandidateLastname) &&
                !firstCandidateLastname.isEmpty();
        boolean isDateEquals = firstCandidateDate.equals(secondCandidateDate) &&
                !firstCandidateDate.isEmpty();
        boolean isCityEquals = firstCandidateCity.equals(secondCandidateCity) &&
                !firstCandidateCity.isEmpty();

        if(isFirstnameEquals && isLastnameEquals &&
                isDateEquals && isCityEquals){
            return 0;
        }
        return -1;
    }
}

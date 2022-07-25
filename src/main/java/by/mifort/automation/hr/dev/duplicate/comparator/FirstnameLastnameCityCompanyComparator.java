package by.mifort.automation.hr.dev.duplicate.comparator;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.duplicate.DuplicatesStrategyUtils;

import java.util.Comparator;
import java.util.List;

public class FirstnameLastnameCityCompanyComparator implements Comparator<Candidate> {

    private static final String FIRSTNAME_IN_DB = "firstname";
    private static final String LASTNAME_IN_DB = "lastname";
    private static final String CITY_IN_DB = "city";
    private static final String COMPANY_IN_DB = "company";

    @Override
    public int compare(Candidate o1, Candidate o2) {
        List<CandidateAttributes> o1Attr = o1.getAttributes();
        List<CandidateAttributes> o2Attr = o2.getAttributes();

        String firstCandidateFirstname = DuplicatesStrategyUtils.setValues(o1Attr, FIRSTNAME_IN_DB);
        String firstCandidateLastname = DuplicatesStrategyUtils.setValues(o1Attr, LASTNAME_IN_DB);
        String firstCandidateCompany = DuplicatesStrategyUtils.setValues(o1Attr, COMPANY_IN_DB);
        String firstCandidateCity = DuplicatesStrategyUtils.setValues(o1Attr, CITY_IN_DB);

        String secondCandidateFirstname = DuplicatesStrategyUtils.setValues(o2Attr, FIRSTNAME_IN_DB);
        String secondCandidateLastname = DuplicatesStrategyUtils.setValues(o2Attr, LASTNAME_IN_DB);
        String secondCandidateCompany = DuplicatesStrategyUtils.setValues(o2Attr, COMPANY_IN_DB);
        String secondCandidateCity = DuplicatesStrategyUtils.setValues(o2Attr, CITY_IN_DB);

        boolean isFirstnameEquals = firstCandidateFirstname.equals(secondCandidateFirstname) &&
                !firstCandidateFirstname.isEmpty();
        boolean isLastnameEquals = firstCandidateLastname.equals(secondCandidateLastname) &&
                !firstCandidateLastname.isEmpty();
        boolean isCompanyEquals = firstCandidateCompany.equals(secondCandidateCompany) &&
                !firstCandidateCompany.isEmpty();
        boolean isCityEquals = firstCandidateCity.equals(secondCandidateCity) &&
                !firstCandidateCity.isEmpty();
        if(isFirstnameEquals && isLastnameEquals &&
                isCompanyEquals && isCityEquals){
            return 0;
        }
        return -1;
    }
}

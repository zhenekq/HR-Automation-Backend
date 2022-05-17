package by.mifort.automation.hr.dev.service.duplicates.comparator;

import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyUtils;

import java.util.Comparator;
import java.util.List;

public class KeyAttributeComparator implements Comparator<Candidate> {

    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String TWITTER = "twitter";
    private static final String LINKEDIN = "linkedin";
    private static final String GITHUB_ID = "githubid";
    private static final String FACEBOOK_ID = "facebookid";
    private static final String TELEGRAM = "telegram";

    @Override
    public int compare(Candidate o1, Candidate o2) {
        List<CandidateAttributes> o1Attr = o1.getAttributes();
        List<CandidateAttributes> o2Attr = o2.getAttributes();

        String firstEmail = DuplicatesStrategyUtils.setValues(o1Attr, EMAIL);
        String firstPhone = DuplicatesStrategyUtils.setValues(o1Attr, PHONE);
        String firstTwitter = DuplicatesStrategyUtils.setValues(o1Attr, TWITTER);
        String firstLinkedin = DuplicatesStrategyUtils.setValues(o1Attr, LINKEDIN);
        String firstGithub = DuplicatesStrategyUtils.setValues(o1Attr, GITHUB_ID);
        String firstFacebook = DuplicatesStrategyUtils.setValues(o1Attr, FACEBOOK_ID);
        String firstTelegram = DuplicatesStrategyUtils.setValues(o1Attr, TELEGRAM);

        String secondEmail = DuplicatesStrategyUtils.setValues(o2Attr, EMAIL);
        String secondPhone = DuplicatesStrategyUtils.setValues(o2Attr, PHONE);
        String secondTwitter = DuplicatesStrategyUtils.setValues(o2Attr, TWITTER);
        String secondLinkedin = DuplicatesStrategyUtils.setValues(o2Attr, LINKEDIN);
        String secondGithub = DuplicatesStrategyUtils.setValues(o2Attr, GITHUB_ID);
        String secondFacebook = DuplicatesStrategyUtils.setValues(o2Attr, FACEBOOK_ID);
        String secondTelegram = DuplicatesStrategyUtils.setValues(o2Attr, TELEGRAM);

        boolean isIdenticalEmails = firstEmail.equals(secondEmail) && !firstEmail.isEmpty();
        boolean isIdenticalPhones = firstPhone.equals(secondPhone) && !firstPhone.isEmpty();
        boolean isIdenticalTwitters = firstTwitter.equals(secondTwitter) && !firstTwitter.isEmpty();
        boolean isIdenticalLinkedIns = firstLinkedin.equals(secondLinkedin) && !firstLinkedin.isEmpty();
        boolean isIdenticalGithub = firstGithub.equals(secondGithub) && !firstGithub.isEmpty();
        boolean isIdenticalFacebook = firstFacebook.equals(secondFacebook) && !firstFacebook.isEmpty();
        boolean isIdenticalTelegram = firstTelegram.equals(secondTelegram) && !firstTelegram.isEmpty();

        if(isIdenticalEmails || isIdenticalPhones || isIdenticalTwitters || isIdenticalLinkedIns ||
                isIdenticalGithub || isIdenticalFacebook || isIdenticalTelegram
        ){
            return 0;
        }
        return  -1;
    }
}

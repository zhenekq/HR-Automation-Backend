package by.mifort.automation.hr.dev.shared.exception.storage;

public class CandidateExceptionResponseStorage {

    private CandidateExceptionResponseStorage() {
    }

    public static String candidateExists(String candidateId) {
        return String.format("Candidate with id '%s' already exists!", candidateId);
    }

    public static String candidateNotExists(String candidateId) {
        return String.format("Candidate with id '%s' not exists!", candidateId);
    }
}

package by.mifort.automation.hr.dev.shared.exception.storage;

public class MergeExceptionResponseStatus {
    private MergeExceptionResponseStatus() {
    }

    public static String mergeNotFound(Integer id) {
        return String.format("Merge with id '%d' not exists!", id);
    }
}

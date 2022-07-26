package by.mifort.automation.hr.dev.shared.exception.storage;

public class HistoryExceptionResponseStorage {
    private HistoryExceptionResponseStorage() {
    }

    public static String historyNotFound(Integer historyId) {
        return String.format("History with id '%d' not found", historyId);
    }
}

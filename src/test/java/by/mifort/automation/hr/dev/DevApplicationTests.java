package by.mifort.automation.hr.dev;

import by.mifort.automation.hr.dev.candidate.CandidateDto;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DevApplicationTests {
    @Test
    void contextLoads() {
        CandidateDto expected = new CandidateDto("1", new Timestamp(2L), "status");
        CandidateDto actual = new CandidateDto("1", new Timestamp(2L), "status");
        assertEquals(expected, actual);
    }
}

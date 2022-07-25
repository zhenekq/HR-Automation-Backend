package by.mifort.automation.hr.dev.keyword;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository repository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public KeywordServiceImpl(KeywordRepository repository, CandidateRepository candidateRepository) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Keyword> createByCandidateId(String id, FilterDto dto) {
        candidateRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(id), HttpStatus.NOT_FOUND));
        List<Keyword> requestKeywords = dto.getKeyword()
                .stream()
                .map(Keyword::new)
                .collect(Collectors.toList());
        List<Keyword> distinctKeywords = new ArrayList<>();
        for (Keyword keyword : requestKeywords) {
            Keyword dbKeyword = repository.findById(keyword.getId()).orElse(null);
            if (dbKeyword == null) {
                distinctKeywords.add(keyword);
            }
        }
        distinctKeywords.forEach(el -> el.setCandidate(new Candidate(id)));
        repository.saveAll(distinctKeywords);
        return distinctKeywords;
    }


}

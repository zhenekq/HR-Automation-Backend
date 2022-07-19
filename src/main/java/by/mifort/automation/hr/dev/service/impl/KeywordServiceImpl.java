package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.Keyword;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.repository.KeywordRepository;
import by.mifort.automation.hr.dev.service.KeywordService;
import by.mifort.automation.hr.dev.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        if (candidateRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(StringUtil.candidateTypeException(id));
        }
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

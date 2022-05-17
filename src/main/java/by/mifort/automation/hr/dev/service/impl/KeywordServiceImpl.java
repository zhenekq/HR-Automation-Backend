package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.Keyword;
import by.mifort.automation.hr.dev.repository.KeywordRepository;
import by.mifort.automation.hr.dev.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository repository;

    @Autowired
    public KeywordServiceImpl(KeywordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Keyword> createByCandidateId(String id, FilterDto dto) {
        List<Keyword> requestKeywords = dto.getKeyword()
                .stream()
                .map(Keyword::new)
                .toList();
        List<Keyword> distinctKeywords = new ArrayList<>();
        for (Keyword keyword : requestKeywords) {
            Keyword dbKeyword = repository.findById(keyword.getId()).orElse(null);
            if (dbKeyword == null) {
                distinctKeywords.add(keyword);
            }
        }
        distinctKeywords.forEach((el) -> el.setCandidate(new Candidate(id)));
        repository.saveAll(distinctKeywords);
        return distinctKeywords;
    }


}

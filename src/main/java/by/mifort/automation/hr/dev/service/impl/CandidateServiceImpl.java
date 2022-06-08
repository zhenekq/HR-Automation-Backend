package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.repository.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.service.CandidateService;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateAttributesRepository candidateAttributesRepository;
    private final EntityValidator<Candidate> validator;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateAttributesRepository candidateAttributesRepository, EntityValidator<Candidate> validator) {
        this.candidateRepository = candidateRepository;
        this.candidateAttributesRepository = candidateAttributesRepository;
        this.validator = validator;
    }

    @Override
    public List<Candidate> getAll(FilterDto filterDto) {
        List<Candidate> candidateList;
        Integer page = filterDto.getPageNumber();
        Integer amount = filterDto.getPageSize();
        Pageable pageable = PageRequest.of(page - 1, amount);
        if (filterDto.getKeyword() == null) {
            candidateList = candidateRepository.findAll(pageable).toList();
            return candidateList;
        }
        candidateList = candidateRepository.findCandidatesByKeywordsContaining(filterDto.getKeyword().get(0), pageable);
        return candidateList;
    }

    @Override
    @Transactional
    public Candidate getById(@NotNull String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate with id: " + id + " not found!"));
    }

    @Override
    @Transactional
    public Candidate create(Candidate candidate) {
        if(validator.isValidParams(candidate)){
            boolean isCandidateExists = candidateRepository
                    .findById(candidate.getId())
                    .isPresent();
            if(!isCandidateExists){
                candidate.setLastContact(new Timestamp(new Date().getTime()));
                candidate.setStatus("created");
                candidateRepository.save(candidate);
                candidateAttributesRepository.saveAll(emptyList(candidate));
                return candidate;
            }else{
                throw new EntityExistsException("Candidate with id: " + candidate.getId() + " exists!");
            }
        }
        throw new IllegalArgumentException("Fields cannot be nullable");
    }

    private List<CandidateAttributes> emptyList(Candidate candidate){
        return new ArrayList<>(List.of(
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(1), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(2), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(3), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(4), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(5), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(6), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(7), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(8), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(9), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(10), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(11), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(12), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(13), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(14), Boolean.FALSE),
                new CandidateAttributes("", 0,new Candidate(candidate.getId()), new AttributeTypes(15), Boolean.FALSE)
        ));
    }
}

package by.mifort.automation.hr.dev.candidate;

import by.mifort.automation.hr.dev.attribute.CandidateAttributes;
import by.mifort.automation.hr.dev.attribute.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataCreateException;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.type.AttributeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateAttributesRepository candidateAttributesRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateAttributesRepository candidateAttributesRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateAttributesRepository = candidateAttributesRepository;
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
    public Candidate getById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(
                        () -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(id), HttpStatus.NOT_FOUND)
                );
    }

    @Override
    @Transactional
    public Candidate create(Candidate candidate) {
        String candidateId = candidate.getId();
        boolean isCandidateExists = candidateRepository
                .findById(candidate.getId())
                .isPresent();
        if (!isCandidateExists) {
            candidate.setLastContact(new Timestamp(new Date().getTime()));
            candidate.setStatus(CandidateStatus.CREATED.toString());
            candidateRepository.save(candidate);
            candidateAttributesRepository.saveAll(emptyList(candidate));
            return candidate;
        }
        throw new DataCreateException(CandidateExceptionResponseStorage.candidateExists(candidateId), HttpStatus.CONFLICT);
    }

    private List<CandidateAttributes> emptyList(Candidate candidate) {
        return new ArrayList<>(List.of(
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(1), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(2), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(3), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(4), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(5), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(6), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(7), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(8), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(9), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(10), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(11), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(12), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(13), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(14), Boolean.FALSE),
                new CandidateAttributes("", 0, new Candidate(candidate.getId()), new AttributeTypes(15), Boolean.FALSE)
        ));
    }
}

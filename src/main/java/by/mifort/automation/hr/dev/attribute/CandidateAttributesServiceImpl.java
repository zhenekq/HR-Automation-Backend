package by.mifort.automation.hr.dev.attribute;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateRepository;
import by.mifort.automation.hr.dev.shared.differences.impl.AssertDifferencesCandidateListAttributes;
import by.mifort.automation.hr.dev.shared.exception.storage.AttributeTypeExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.storage.CandidateExceptionResponseStorage;
import by.mifort.automation.hr.dev.shared.exception.varieties.DataNotFoundException;
import by.mifort.automation.hr.dev.type.AttributeTypesRepository;
import by.mifort.automation.hr.dev.update.CandidateUpdateRepository;
import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateAttributesServiceImpl implements CandidateAttributesService {

    private final CandidateAttributesRepository repository;
    private final CandidateRepository candidateRepository;
    private final AttributeTypesRepository typesRepository;
    private final CandidateUpdateRepository candidateUpdateRepository;

    @Override
    public List<CandidateAttributes> getByCandidateId(String candidateId) {
        candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new DataNotFoundException(CandidateExceptionResponseStorage.candidateNotExists(candidateId), HttpStatus.NOT_FOUND));
        return repository.findAllByCandidateId(candidateId);
    }

    @Override
    @Transactional
    public List<CandidateAttributes> createByCandidateId(String candidateId, List<CandidateAttributes> attributes) {
        List<CandidateAttributes> candidateAttributes = getByCandidateId(candidateId);
        CandidateUpdate update = AssertDifferencesCandidateListAttributes.getUpdates(candidateAttributes, attributes);
        attributes.forEach(
                p -> p.setCandidate(new Candidate(candidateId))
        );
        attributes.forEach(
                attribute -> typesRepository
                        .findById(attribute.getAttributeTypes().getId())
                        .orElseThrow(() -> new DataNotFoundException(AttributeTypeExceptionResponseStorage.attributeTypeNotFound(attribute.getId()), HttpStatus.NOT_FOUND))
        );
        List<CandidateAttributes> attr = AssertDifferencesCandidateListAttributes.assertDiff(
                candidateAttributes, attributes);
        repository.saveAll(attr);
        if (update.getChangeSet().size() != 0 && !update.getChangeSet().isEmpty() && update.getChangeSet() != null) {
            candidateUpdateRepository.save(update);
        }
        attr.forEach(p -> p.setAttributeTypes(typesRepository.getById(p.getAttributeTypes().getId())));
        return attr;

    }

    @Override
    public List<CandidateAttributes> archiveByCandidateId(String candidateId, List<Integer> attributesId) {
        return Collections.emptyList();
    }
}

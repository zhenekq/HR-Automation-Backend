package by.mifort.automation.hr.dev.service.impl;

import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.repository.AttributeTypesRepository;
import by.mifort.automation.hr.dev.repository.CandidateAttributesRepository;
import by.mifort.automation.hr.dev.repository.CandidateRepository;
import by.mifort.automation.hr.dev.repository.CandidateUpdateRepository;
import by.mifort.automation.hr.dev.service.CandidateAttributesService;
import by.mifort.automation.hr.dev.util.StringUtil;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import by.mifort.automation.hr.dev.util.differences.impl.AsserDifferencesCandidateListAttributes;
import by.mifort.automation.hr.dev.util.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class CandidateAttributesServiceImpl implements CandidateAttributesService {

    private final CandidateAttributesRepository repository;
    private final CandidateRepository candidateRepository;
    private final AttributeTypesRepository typesRepository;
    private final EntityConverter<CandidateAttributes, CandidateAttributesDto> converter;
    private final CandidateUpdateRepository candidateUpdateRepository;
    private final EntityValidator<List<CandidateAttributes>> validator;

    @Autowired
    public CandidateAttributesServiceImpl(CandidateAttributesRepository repository, CandidateRepository candidateRepository, AttributeTypesRepository typesRepository, EntityConverter<CandidateAttributes, CandidateAttributesDto> converter, CandidateUpdateRepository candidateUpdateRepository, EntityValidator<List<CandidateAttributes>> validator) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
        this.typesRepository = typesRepository;
        this.converter = converter;
        this.candidateUpdateRepository = candidateUpdateRepository;
        this.validator = validator;
    }

    @Override
    public List<CandidateAttributes> getByCandidateId(String candidateId) {
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new EntityNotFoundException(StringUtil.candidateTypeException(candidateId));
        }
        return repository.findAllByCandidateId(candidateId);
    }

    @Override
    @Transactional
    public List<CandidateAttributes> createByCandidateId(String candidateId, List<CandidateAttributes> attributes) {
        List<CandidateAttributes> candidateAttributes = getByCandidateId(candidateId);
        CandidateUpdate update = AsserDifferencesCandidateListAttributes.getUpdates(candidateAttributes, attributes);
        if (candidateRepository.findById(candidateId).isEmpty()) {
            throw new EntityNotFoundException(StringUtil.candidateTypeException(candidateId));
        }
        attributes.forEach(
                p -> p.setCandidate(new Candidate(candidateId))
        );
        attributes.forEach(
                attribute -> typesRepository
                        .findById(attribute.getAttributeTypes().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Attribute type not found!"))
        );
        if (validator.isValidParams(attributes)) {
            List<CandidateAttributes> attr = AsserDifferencesCandidateListAttributes.assertDiff(
                    candidateAttributes, attributes);
            repository.saveAll(attr);
            if(update.getChangeSet().size() != 0 && !update.getChangeSet().isEmpty() && update.getChangeSet() != null){
                candidateUpdateRepository.save(update);
            }
            attr.forEach(p -> p.setAttributeTypes(typesRepository.getById(p.getAttributeTypes().getId())));
            return attr;
        }
        throw new IllegalArgumentException("Entity params couldn't be nullable!");
    }

    @Override
    public List<CandidateAttributes> archiveByCandidateId(String candidateId, List<Integer> attributesId) {
        return Collections.emptyList();
    }
}

package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategy;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyFactory;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyName;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates/duplicates")
public class DuplicateController {

    private final DuplicatesStrategyFactory factory;
    private final EntityConverter<Candidate, CandidateDto> converter;

    public DuplicateController(DuplicatesStrategyFactory factory, EntityConverter<Candidate, CandidateDto> converter) {
        this.factory = factory;
        this.converter = converter;
    }

    @GetMapping
    public List<List<CandidateDto>> getByAttributes(@RequestParam String strategy){
        DuplicatesStrategyName strategyName = DuplicatesStrategyName.valueOf(strategy.toUpperCase());
        DuplicatesStrategy duplicatesStrategy = factory.findStrategy(strategyName);
        List<List<Candidate>> duplicates = duplicatesStrategy.getDuplicates();
        return duplicates
                .stream()
                .map(converter::convertToListEntityDto)
                .collect(Collectors.toList());
    }
}

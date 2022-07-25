package by.mifort.automation.hr.dev.duplicate;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.candidate.CandidateDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates/duplicates")
public class DuplicateController {

    private final DuplicatesStrategyFactory factory;
    private final ModelMapper modelMapper;

    public DuplicateController(DuplicatesStrategyFactory factory, ModelMapper modelMapper) {
        this.factory = factory;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<List<CandidateDto>> getByAttributes(@RequestParam String strategy){
        DuplicatesStrategyName strategyName = DuplicatesStrategyName.valueOf(strategy.toUpperCase());
        DuplicatesStrategy duplicatesStrategy = factory.findStrategy(strategyName);
        List<List<Candidate>> duplicates = duplicatesStrategy.getDuplicates();
        return duplicates
                .stream()
                .map(el ->
                        el.stream().map(el2 -> modelMapper.map(el2, CandidateDto.class))
                                .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }
}

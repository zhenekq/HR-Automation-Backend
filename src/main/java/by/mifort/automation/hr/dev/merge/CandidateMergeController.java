package by.mifort.automation.hr.dev.merge;

import by.mifort.automation.hr.dev.candidate.Candidate;
import by.mifort.automation.hr.dev.merge.request.CreateMergeRequest;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates/merge")
@AllArgsConstructor
@Validated
public class CandidateMergeController {

    private final CandidateMergeService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CandidateMergeDto> getAll(@Valid FilterDto dto) {
        List<CandidateMerge> candidateMergeList = service.getAll(dto);
        return candidateMergeList
                .stream()
                .map(el -> modelMapper.map(el, CandidateMergeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CandidateMergeDto getById(@PathVariable Integer id) {
        CandidateMerge merge = service.getById(id);
        return modelMapper.map(merge, CandidateMergeDto.class);
    }

    @PostMapping
    public CandidateMergeDto create(@Valid @RequestBody CreateMergeRequest mergeRequest) {
        CandidateMerge merge = modelMapper.map(mergeRequest, CandidateMerge.class);
        merge.setCandidate1(new Candidate(mergeRequest.getCandidate1()));
        merge.setCandidate2(new Candidate(mergeRequest.getCandidate2()));
        CandidateMerge createdMerge = service.create(merge);
        return modelMapper.map(createdMerge, CandidateMergeDto.class);
    }

    @PostMapping ("{id}")
    public CandidateMergeDto mergeById(@PathVariable Integer id) {
        CandidateMerge merge = service.mergeById(id);
        return modelMapper.map(merge, CandidateMergeDto.class);
    }

}

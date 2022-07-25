package by.mifort.automation.hr.dev.candidate;

import by.mifort.automation.hr.dev.candidate.request.CreateNewCandidateRequest;
import by.mifort.automation.hr.dev.keyword.Keyword;
import by.mifort.automation.hr.dev.keyword.KeywordService;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles requests about candidates
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/candidates")
@Validated
@Api("Candidate controller for searching and creating new")
public class CandidateController {

    private final CandidateService candidateService;
    private final KeywordService keywordService;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateController(CandidateService candidateService, KeywordService keywordService, ModelMapper modelMapper) {
        this.candidateService = candidateService;
        this.keywordService = keywordService;
        this.modelMapper = modelMapper;
    }

    /**
     * GET request to receive all info about candidates
     * Also with filters such as keywords and attributes
     *
     * @param filterDto entity with pageSize, pageNum, and other filter configuration
     * @return Full list of candidates or by keyboards or not
     * @see FilterDto
     */
    @ApiOperation("Get paginated candidates, with amount of them on one page")
    @GetMapping
    public List<CandidateDto> getAll(@Valid FilterDto filterDto) {
        List<Candidate> candidateList = candidateService.getAll(filterDto);
        return candidateList
                .stream()
                .map(el -> modelMapper.map(el, CandidateDto.class))
                .collect(Collectors.toList());
    }

    /**
     * GET request to receive info about candidate by id
     *
     * @param id unique identifier for searching
     * @return Candidate info by id
     */
    @ApiOperation("Get full information about user by id")
    @GetMapping("/{id}")
    public CandidateDto getById(@PathVariable @NotBlank String id) {
        Candidate candidate = candidateService.getById(id);
        return modelMapper.map(candidate, CandidateDto.class);
    }

    /**
     * POST request to create candidate
     *
     * @param candidateRequest candidate entity body
     * @return Conversation of successful created candidate with id
     */
    @ApiOperation("Create new candidate")
    @PostMapping
    public CandidateDto create(@Valid @RequestBody CreateNewCandidateRequest candidateRequest) {
        Candidate candidate = modelMapper.map(candidateRequest, Candidate.class);
        Candidate createdCandidate = candidateService.create(candidate);
        return modelMapper.map(createdCandidate, CandidateDto.class);
    }

    @ApiOperation("Connect keywords to candidate")
    @PostMapping("/{id}")
    public List<Keyword> addKeywords(@PathVariable String id,
                                     FilterDto filterDto) {
        return keywordService.createByCandidateId(id, filterDto);
    }
}

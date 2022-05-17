package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CandidateDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.entity.Keyword;
import by.mifort.automation.hr.dev.service.CandidateService;
import by.mifort.automation.hr.dev.service.KeywordService;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategy;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyFactory;
import by.mifort.automation.hr.dev.service.duplicates.DuplicatesStrategyName;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Controller that handles requests about candidates
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/candidates")
@Api("Candidate controller for searching and creating new")
public class CandidateController {

    private final CandidateService candidateService;
    private final KeywordService keywordService;
    private final EntityConverter<Candidate, CandidateDto> converter;
    private final DuplicatesStrategyFactory factory;

    @Autowired
    public CandidateController(CandidateService candidateService, KeywordService keywordService, EntityConverter<Candidate, CandidateDto> converter, DuplicatesStrategyFactory factory) {
        this.candidateService = candidateService;
        this.keywordService = keywordService;
        this.converter = converter;
        this.factory = factory;
    }

    //STATEGY PATTERN

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
    public List<CandidateDto> getAll(FilterDto filterDto) {
        Integer pageNumber = filterDto.getPageNumber();
        Integer pageSize = filterDto.getPageSize();
        if (pageNumber == null || pageSize == null || pageNumber <= 0 || pageSize <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters cannot be nullable");
        }
        List<Candidate> candidateList = candidateService.getAll(filterDto);
        return converter.convertToListEntityDto(candidateList);
    }

    /**
     * GET request to receive info about candidate by id
     *
     * @param id unique identifier for searching
     * @return Candidate info by id
     */
    @ApiOperation("Get full information about user by id")
    @GetMapping("/{id}")
    public CandidateDto getById(@PathVariable String id) {
        Candidate candidate = candidateService.getById(id);
        return converter.convertToEntityDto(candidate);
    }

    /**
     * POST request to create candidate
     *
     * @param candidate candidate entity body
     * @return Conversation of successful created candidate with id
     */
    @ApiOperation("Create new candidate")
    @PostMapping
    public CandidateDto create(@RequestBody Candidate candidate) {
        Candidate createdCandidate = candidateService.create(candidate);
        return converter.convertToEntityDto(createdCandidate);
    }

    @ApiOperation("Connect keywords to candidate")
    @PostMapping("/{id}")
    public List<Keyword> addKeywords(@PathVariable String id,
                                     FilterDto filterDto){
        Candidate candidate = candidateService.getById(id);
        return keywordService.createByCandidateId(id, filterDto);
    }
}

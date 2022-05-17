package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CandidateAttributesDto;
import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import by.mifort.automation.hr.dev.service.CandidateAttributesService;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that handles requests about candidate's attributes
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/candidates/{id}/attributes")
@Api("Controller for manipulate with attributes of candidate")
public class CandidateAttributeController {

    private final CandidateAttributesService service;
    private final EntityConverter<CandidateAttributes, CandidateAttributesDto> converter;

    @Autowired
    public CandidateAttributeController(CandidateAttributesService service, EntityConverter<CandidateAttributes, CandidateAttributesDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    /**
     * GET request to get candidate's attributes
     *
     * @param id candidate identifier
     * @return CandidateAttributes of candidate
     */
    @ApiOperation("Get attributes of candidate by his id")
    @GetMapping
    public List<CandidateAttributesDto> getByCandidateId(@PathVariable String id) {
        List<CandidateAttributes> candidateAttributes = service.getByCandidateId(id);
        return converter.convertToListEntityDto(candidateAttributes);
    }

    /**
     * POST request to create attributes with candidate
     *
     * @param id         candidate identifier
     * @param attributes body of attributes
     * @return Candidate's id
     */
    @ApiOperation("Create new attributes with candidate by his id")
    @PostMapping
    public List<CandidateAttributesDto> createByCandidateId(@PathVariable String id,
                                                            @RequestBody List<CandidateAttributes> attributes) {
        List<CandidateAttributes> attributesList = service.createByCandidateId(id, attributes);
        return converter.convertToListEntityDto(attributesList);
    }

    @ApiOperation("Archived candidate attribute by his id")
    @DeleteMapping
    public List<CandidateAttributesDto> archiveByCandidateId(@PathVariable String id,
                                                             @RequestParam List<Integer> attributesId){
        List<CandidateAttributes> attributes = service.archiveByCandidateId(id, attributesId);
        return converter.convertToListEntityDto(attributes);
    }
}

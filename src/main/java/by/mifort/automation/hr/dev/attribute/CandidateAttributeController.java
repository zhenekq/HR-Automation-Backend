package by.mifort.automation.hr.dev.attribute;

import by.mifort.automation.hr.dev.attribute.request.CreateUpdateCandidateAttributeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles requests about candidate's attributes
 *
 * @author yauheni_vozny
 * @version 1.0
 */

@RestController
@RequestMapping("/candidates/{id}/attributes")
@Api("Controller for manipulate with attributes of candidate")
@Validated
@AllArgsConstructor
public class CandidateAttributeController {

    private final CandidateAttributesService service;
    private final ModelMapper modelMapper;

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
        List<CandidateAttributesDto> attributes = candidateAttributes
                .stream()
                .map(el -> modelMapper.map(el, CandidateAttributesDto.class))
                .collect(Collectors.toList());
        CandidateAttributeUtil.addLabelList(candidateAttributes, attributes);
        return attributes;
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
                                                            @Valid @RequestBody List<CreateUpdateCandidateAttributeRequest> attributes) {
        List<CandidateAttributes> fullAttributes = attributes
                .stream()
                .map(el -> modelMapper.map(el, CandidateAttributes.class))
                .collect(Collectors.toList());
        List<CandidateAttributes> attributesList = service.createByCandidateId(id, fullAttributes);
        return attributesList
                .stream()
                .map(el -> modelMapper.map(el, CandidateAttributesDto.class))
                .collect(Collectors.toList());
    }

    @ApiOperation("Archived candidate attribute by his id")
    @DeleteMapping
    public List<CandidateAttributesDto> archiveByCandidateId(@PathVariable String id,
                                                             @RequestParam List<Integer> attributesId) {
        List<CandidateAttributes> attributes = service.archiveByCandidateId(id, attributesId);
        return attributes
                .stream()
                .map(el -> modelMapper.map(el, CandidateAttributesDto.class))
                .collect(Collectors.toList());
    }
}

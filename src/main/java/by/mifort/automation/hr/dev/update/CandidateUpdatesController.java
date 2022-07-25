package by.mifort.automation.hr.dev.update;

import by.mifort.automation.hr.dev.update.data.CandidateUpdate;
import by.mifort.automation.hr.dev.update.data.CandidateUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles requests about candidates updates
 *
 * @author yauheni_vozny
 * @version 1.0
 */
@RestController
@RequestMapping("/candidates/{id}/updates")
@Api("Candidate updates controller for manipulating with it")
public class CandidateUpdatesController {

    private final CandidateUpdatesService service;
    private final ModelMapper modelMapper;

    @Autowired
    public CandidateUpdatesController(CandidateUpdatesService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    /**
     * GET request to receive all info about candidate's updates
     *
     * @param id candidate's identifier
     * @return Full list of candidate's updates
     */

    @GetMapping
    @ApiOperation("Get all updates by candidate identifier")
    public List<CandidateUpdateDto> getByCandidateId(@PathVariable String id) {
        List<CandidateUpdate> candidateUpdates = service.getByCandidateId(id);
        return candidateUpdates
                .stream()
                .map(el -> modelMapper.map(el, CandidateUpdateDto.class))
                .collect(Collectors.toList());
    }

    /**
     * POST request to create new candidate update
     *
     * @param update body of update
     * @return New candidate update
     */
    @PostMapping
    @ApiOperation("Create new update for candidate")
    public CandidateUpdateDto create(@PathVariable String id, @RequestBody CandidateUpdateDto update) {
        CandidateUpdate candidateUpdate = modelMapper.map(update, CandidateUpdate.class);
        CandidateUpdate newUpdate = service.createByCandidateId(id, candidateUpdate);
        return modelMapper.map(newUpdate, CandidateUpdateDto.class);
    }

}

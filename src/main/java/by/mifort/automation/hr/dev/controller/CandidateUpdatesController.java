package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CandidateUpdateDto;
import by.mifort.automation.hr.dev.dto.ChangeSet;
import by.mifort.automation.hr.dev.dto.Type;
import by.mifort.automation.hr.dev.entity.Candidate;
import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import by.mifort.automation.hr.dev.service.CandidateUpdatesService;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

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
    private final EntityConverter<CandidateUpdate, CandidateUpdateDto> converter;

    @Autowired
    public CandidateUpdatesController(CandidateUpdatesService service, EntityConverter<CandidateUpdate, CandidateUpdateDto> converter) {
        this.service = service;
        this.converter = converter;
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
        return converter.convertToListEntityDto(service.getByCandidateId(id));
    }

    /**
     * POST request to create new candidate update
     *
     * @param update body of update
     * @return New candidate update
     */
    @PostMapping
    @ApiOperation("Create new update for candidate")
    public CandidateUpdateDto create(@PathVariable String id, @RequestBody CandidateUpdate update){
        CandidateUpdate newUpdate = service.createByCandidateId(id, update);
        return converter.convertToEntityDto(newUpdate);
    }

}

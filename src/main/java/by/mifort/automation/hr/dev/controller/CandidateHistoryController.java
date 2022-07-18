package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.CommunicationHistory;
import by.mifort.automation.hr.dev.service.CommunicationHistoryService;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that handles requests about candidate's history communication
 *
 * @author yauheni_vozny
 * @version 1.0
 */
@RestController
@RequestMapping("/candidates/{id}/history")
@Api("Controller for manipulate with history of candidate")
public class CandidateHistoryController {

    private final CommunicationHistoryService service;
    private final EntityConverter<CommunicationHistory, CommunicationHistoryDto> converter;

    public CandidateHistoryController(CommunicationHistoryService service, EntityConverter<CommunicationHistory, CommunicationHistoryDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    /**
     * GET request to get candidate's history
     *
     * @param id - candidate identifier
     * @param filterDto pagination by history (archived, or not)
     * @return CommunicationHistory of candidate
     */
    @ApiOperation("Get all archived or not archived histories with candidate by his id")
    @GetMapping
    public List<CommunicationHistoryDto> getByCandidateId(@PathVariable String id,
                                                          FilterDto filterDto) {
        List<CommunicationHistory> historyList = service.getByCandidateId(id, filterDto);
        return converter.convertToListEntityDto(historyList);
    }

    /**
     * POST request to create history with candidate
     *
     * @param id      candidate identifier
     * @param history body of history
     * @return Candidate's id
     */
    @ApiOperation("Create new history with candidate by his id")
    @PostMapping
    public CommunicationHistoryDto createByCandidateId(@PathVariable String id,
                                                       @RequestBody CommunicationHistoryDto history) {
        CommunicationHistory createdHistory = service.createByCandidateId(id, converter.convertToEntity(history));
        return converter.convertToEntityDto(createdHistory);
    }

    /**
     * PATCH request to update history with candidate
     *
     * @param id      candidate identifier
     * @param history body of history
     * @return Candidate's id
     */
    @ApiOperation("Update history with candidate by his id")
    @PatchMapping
    public CommunicationHistoryDto updateByCandidateId(@PathVariable String id,
                                                       @RequestBody CommunicationHistoryDto history) {
        CommunicationHistory updatedHistory = service.updateByCandidateId(id, history);
        return converter.convertToEntityDto(updatedHistory);
    }

    /**
     * DELETE request to archive history with candidate
     *
     * @param id candidate identifier
     * @return Candidate's id
     */
    @ApiOperation("Archive history with candidate with his id")
    @DeleteMapping("/{historyId}")
    public CommunicationHistoryDto archiveHistoryWithCandidate(@PathVariable String id,
                                                               @PathVariable Integer historyId) {
        CommunicationHistory archivedHistory = service.archiveByCandidateId(id, historyId);
        return converter.convertToEntityDto(archivedHistory);
    }
}

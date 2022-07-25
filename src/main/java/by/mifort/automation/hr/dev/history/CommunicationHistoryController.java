package by.mifort.automation.hr.dev.history;

import by.mifort.automation.hr.dev.history.data.CommunicationHistory;
import by.mifort.automation.hr.dev.history.data.CommunicationHistoryDto;
import by.mifort.automation.hr.dev.history.request.CreateNewCommunicationHistory;
import by.mifort.automation.hr.dev.history.request.UpdateExistsCommunicationHistory;
import by.mifort.automation.hr.dev.shared.data.FilterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles requests about candidate's history communication
 *
 * @author yauheni_vozny
 * @version 1.0
 */
@RestController
@RequestMapping("/candidates/{id}/history")
@Api("Controller for manipulate with history of candidate")
@Validated
public class CommunicationHistoryController {

    private final CommunicationHistoryService service;
    private final ModelMapper modelMapper;

    public CommunicationHistoryController(CommunicationHistoryService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
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
        return historyList.stream()
                .map(el -> modelMapper.map(el, CommunicationHistoryDto.class))
                .collect(Collectors.toList());
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
                                                       @Valid @RequestBody CreateNewCommunicationHistory history) {
        CommunicationHistory communicationHistory = modelMapper.map(history, CommunicationHistory.class);
        CommunicationHistory createdHistory = service.createByCandidateId(id, communicationHistory);
        return modelMapper.map(createdHistory, CommunicationHistoryDto.class);
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
                                                       @Valid @RequestBody UpdateExistsCommunicationHistory history) {
        CommunicationHistory communicationHistory = modelMapper.map(history, CommunicationHistory.class);
        CommunicationHistory updatedHistory = service.updateByCandidateId(id, communicationHistory);
        return modelMapper.map(updatedHistory, CommunicationHistoryDto.class);
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
        return modelMapper.map(archivedHistory, CommunicationHistoryDto.class);
    }
}

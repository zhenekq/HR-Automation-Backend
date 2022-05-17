package by.mifort.automation.hr.dev.controller;

import by.mifort.automation.hr.dev.dto.CandidateMergeDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.CandidateMerge;
import by.mifort.automation.hr.dev.service.CandidateMergeService;
import by.mifort.automation.hr.dev.util.converter.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates/merge")
public class CandidateMergeController {

    private final CandidateMergeService service;
    private final EntityConverter<CandidateMerge, CandidateMergeDto> converter;

    @Autowired
    public CandidateMergeController(CandidateMergeService service, EntityConverter<CandidateMerge, CandidateMergeDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<CandidateMergeDto> getAll(FilterDto dto){
        return converter.convertToListEntityDto(service.getAll(dto));
    }

    @GetMapping("{id}")
    public CandidateMergeDto getById(@PathVariable Integer id){
        return converter.convertToEntityDto(service.getById(id));
    }

    @PostMapping
    public CandidateMergeDto create(@RequestBody CandidateMerge merge){
        return converter.convertToEntityDto(service.create(merge));
    }

    @PostMapping ("{id}")
    public CandidateMergeDto mergeById(@PathVariable Integer id){
        return converter.convertToEntityDto(service.mergeById(id));
    }

}

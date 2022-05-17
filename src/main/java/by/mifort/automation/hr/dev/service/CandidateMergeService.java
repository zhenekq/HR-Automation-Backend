package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.CandidateMerge;

import java.util.List;

public interface CandidateMergeService {

    List<CandidateMerge> getAll(FilterDto filterDto);

    CandidateMerge getById(Integer id);

    CandidateMerge create(CandidateMerge candidateMerge);

    CandidateMerge mergeById(Integer id);

}

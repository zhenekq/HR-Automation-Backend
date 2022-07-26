package by.mifort.automation.hr.dev.merge;

import by.mifort.automation.hr.dev.shared.data.FilterDto;

import java.util.List;

public interface CandidateMergeService {

    List<CandidateMerge> getAll(FilterDto filterDto);

    CandidateMerge getById(Integer id);

    CandidateMerge create(CandidateMerge candidateMerge);

    CandidateMerge mergeById(Integer id);

}

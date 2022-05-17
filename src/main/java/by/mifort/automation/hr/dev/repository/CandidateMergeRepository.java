package by.mifort.automation.hr.dev.repository;

import by.mifort.automation.hr.dev.entity.CandidateMerge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMergeRepository extends JpaRepository<CandidateMerge, Integer> {
}

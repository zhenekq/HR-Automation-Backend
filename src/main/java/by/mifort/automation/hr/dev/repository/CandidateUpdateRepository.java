package by.mifort.automation.hr.dev.repository;

import by.mifort.automation.hr.dev.entity.CandidateUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for working with candidate's CandidateUpdate in database
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateUpdate
 */

@Repository
public interface CandidateUpdateRepository extends JpaRepository<CandidateUpdate, Integer> {

    List<CandidateUpdate> findAllByCandidateId(String candidateId);

}

package by.mifort.automation.hr.dev.attribute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for working with candidate's Attributes in database
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see CandidateAttributes
 */

@Repository
public interface CandidateAttributesRepository extends JpaRepository<CandidateAttributes, Integer>, JpaSpecificationExecutor<CandidateAttributes> {

    /**
     * @param id candidate id
     * @return List of candidate attributes by candidate's identifier
     */
    List<CandidateAttributes> findAllByCandidateId(String id);


}

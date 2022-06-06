package by.mifort.automation.hr.dev.repository;

import by.mifort.automation.hr.dev.entity.CandidateAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
     * @param type identifier for searching
     * @return List of probably identical candidate's attributes
     */
    @Query(value =
            "WITH DuplicateValue AS (" +
                    "        SELECT value, COUNT(*) AS CNT" +
                    "        FROM peopleattributes" +
                    "        GROUP BY value" +
                    "        HAVING COUNT(*) > 1" +
                    "   )" +
                    "SELECT * FROM peopleattributes p inner join attributetypes a on p.type=a.id\n" +
                    "where upper(concat('%',a.name,'%')) like upper(concat('%', :type, '%')) and p.value IN (SELECT value from DuplicateValue)", nativeQuery = true)
    @Modifying
    List<CandidateAttributes> findCandidateAttributesByAttributeTypesName(String type);

    /**
     * @param id candidate id
     * @return List of candidate attributes by candidate's identifier
     */
    List<CandidateAttributes> findAllByCandidateId(String id);


}

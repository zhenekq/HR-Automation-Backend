package by.mifort.automation.hr.dev.candidate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for working with Candidate in database
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see Candidate
 */

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {

    /**
     * @param pageable settings to pagination
     * @return Paginated information about people by keywords
     */
    @Query("select p from Candidate p inner join p.keywords kb where kb.id like %:keyword%")
    List<Candidate> findCandidatesByKeywordsContaining(String keyword, Pageable pageable);

    Boolean existsCandidateById(String id);
}

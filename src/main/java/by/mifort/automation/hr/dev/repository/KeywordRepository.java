package by.mifort.automation.hr.dev.repository;

import by.mifort.automation.hr.dev.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for working with candidate's Keywords in database
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see Keyword
 */

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {

    Optional<Keyword> findAllByCandidateId(String candidateId);
}

package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.request.CompletionStage;

@Repository
public interface CompletionStageRepository extends JpaRepository<CompletionStage, Long> {

}

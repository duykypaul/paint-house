package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.dto.TutorialDTO;
import com.duykypaul.painthouse.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>, TutorialCustomRepository {
    List<Tutorial> findByPublished(boolean published);

    @Query(name = "findAll1", nativeQuery = true)
    List<TutorialDTO> findAll1(Integer id);
}

package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TutorialCustomRepository {

  Page<Tutorial> customFindByPublished(boolean published, Pageable pageable);
}

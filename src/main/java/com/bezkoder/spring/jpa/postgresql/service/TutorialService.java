package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;

public interface TutorialService extends GenericService<Tutorial, Long, TutorialDTO>{
}

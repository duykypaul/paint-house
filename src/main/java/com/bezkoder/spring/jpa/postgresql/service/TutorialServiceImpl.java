package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.dto.TutorialDTO;
import com.bezkoder.spring.jpa.postgresql.mapper.TutorialMapper;
import com.bezkoder.spring.jpa.postgresql.model.Tutorial;
import com.bezkoder.spring.jpa.postgresql.repository.TutorialRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorialServiceImpl extends GenericServiceImpl<Tutorial, Long, TutorialDTO> implements TutorialService {

    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        super(tutorialRepository);
    }

    @Override
    public Tutorial toEntity(TutorialDTO element) {
        return TutorialMapper.INSTANCE.toEntity(element);
    }

    @Override
    public TutorialDTO toDTO(Tutorial element) {
        return TutorialMapper.INSTANCE.toDTO(element);
    }
}

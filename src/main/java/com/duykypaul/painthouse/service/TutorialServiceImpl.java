package com.duykypaul.painthouse.service;

import com.duykypaul.painthouse.dto.TutorialDTO;
import com.duykypaul.painthouse.mapper.TutorialMapper;
import com.duykypaul.painthouse.model.Tutorial;
import com.duykypaul.painthouse.repository.TutorialRepository;
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

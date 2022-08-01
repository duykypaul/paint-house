package com.duykypaul.painthouse.repository;

import com.duykypaul.painthouse.common.FileUtils;
import com.duykypaul.painthouse.model.Tutorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TutorialCustomRepositoryImpl implements TutorialCustomRepository {
    @PersistenceContext
    EntityManager entityManager;


    public Page<Tutorial> customFindByPublished(boolean published, Pageable pageable) {

        Query query = entityManager
                .createQuery(FileUtils.readSqlFile("META-INF/sql/findByPublished.sql"), Tutorial.class);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<Tutorial> fooList = query.getResultList();

        Query queryTotal = entityManager.createQuery
                (FileUtils.readSqlFile("META-INF/sql/countFindByPublished.sql"));
        long countResult = (long) queryTotal.getSingleResult();
        int i = (int) countResult;
        return new PageImpl<>(fooList, pageable, i);
    }
}

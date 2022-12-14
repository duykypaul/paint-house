package com.duykypaul.painthouse.model;

import com.duykypaul.painthouse.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @JsonFormat(pattern = Constant.FormatDate.DATE_TIME_DEFAULT)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    @JsonFormat(pattern = Constant.FormatDate.DATE_TIME_DEFAULT)
    private LocalDateTime modifiedAt;

    private Boolean deleteFlag;

    @PrePersist
    public void touchForPersist() {
        if (null == this.deleteFlag) {
            this.deleteFlag = Boolean.FALSE;
        }
    }

    @PreUpdate
    public void touchForUpdate() {
        if (null == this.deleteFlag) {
            this.deleteFlag = Boolean.FALSE;
        }
    }
}
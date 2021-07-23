package com.gmc.gmccoin.common.model.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Auditing
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BoardAuditing extends Auditing {
    @Column(length = 2048)
    private String thumbnailUrl;
    @Column(length = 2048)
    private String imageUrl;
    @Column(length = 2048)
    private String linkUrl;
}
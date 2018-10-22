package xyz.gofastforever.account.domain;

import xyz.gofastforever.account.validation.ValidationRegex;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class BaseEntity {

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  @Column(nullable = false)
  private Date updatedDate;

  @CreatedBy
  @Column(updatable = false)
  @Pattern(regexp = ValidationRegex.UUID_PATTERN)
  private String createdBy;

  @LastModifiedBy
  @Pattern(regexp = ValidationRegex.UUID_PATTERN)
  private String updatedBy;

}
package com.company.resourceapi.entities;

import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "project", uniqueConstraints = @UniqueConstraint(columnNames = {"sdlc_system_id","external_id"}))
@EntityListeners(AuditingEntityListener.class)
@Data @AllArgsConstructor @NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "external_id", nullable = false)
    @NotBlank(message = "ExternalId is mandatory")
    private String externalId;

    @Column(name = "name")
    private String name = "";

    @ManyToOne
    @JoinColumn(name = "sdlc_system_id")
    @NotNull(message = "SdlSystem is mandatory")
    private SdlcSystem sdlcSystem;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;
}

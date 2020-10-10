package com.safeweb.domain;

import liquibase.pro.packaged.C;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@ToString(callSuper = true)
@Entity
@Table(name = "container")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Container implements Serializable {

    private static final long serialVersionUID = 1L;

    @Tolerate
    Container() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @NotNull
    @Column(name = "container_id", nullable = false, length = 128)
    private String containerId;

    @NotNull
    @Column(name = "port", nullable = false)
    private Integer port;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private ContainerStatus status;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "host_id")
    private Host host;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "image_id")
    private Image image;

}

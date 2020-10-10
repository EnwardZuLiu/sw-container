package com.safeweb.domain;

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
@Table(name = "image")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Tolerate
    Image() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tag", nullable = false, length = 50)
    private String tag;

    @NotNull
    @Column(name = "low_ready", nullable = false)
    private Integer lowReady;

    @NotNull
    @Column(name = "high_ready_rate", nullable = false)
    private Double highReadyRate;

    @Column(name = "evns", length = 1000)
    private String envs;

}

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
@Table(name = "host")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Host extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Tolerate
    Host() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "cores")
    private Integer cores;

    @Column(name = "host_name", length = 50)
    private String hostName;

    @Column(name = "os", length = 100)
    private String os;

    @Column(name = "memory_capacity")
    private Long memoryCapacity;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private HostStatus status;

    @NotNull
    @Column(name = "highest_containers", nullable = false)
    private Integer highestContainers;

    @Column(name = "remark")
    private String remark;

}

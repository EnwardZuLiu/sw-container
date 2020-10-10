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
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@ToString(callSuper = true)
@Entity
@Table(name = "connection", indexes = {
    @Index(name = "uid_uuid", columnList = "uuid", unique = true)
})
// 这个注解会自动忽略掉字段为null的插入
@DynamicInsert
// 这个注解会自动忽略掉字段没有修改的更新（更新语句中，字段没有修改则不会发送数据）
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Connection extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Tolerate
    Connection() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 20, max = 50)
    @Column(name = "uuid", nullable = false, length = 50)
    private String uuid;

    @Size(min = 12, max = 255)
    @NotNull
    @Column(name = "container_id", nullable = false)
    private String containerId;

    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "port", nullable = false)
    private Long port;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ConnectionStatus status;

    @Column(name = "remark", length = 1000)
    private String remark;

}

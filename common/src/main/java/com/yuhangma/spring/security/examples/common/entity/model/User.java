package com.yuhangma.spring.security.examples.common.entity.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Moore
 * @since 2019-08-01
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User extends AbstractModel {

    private String username;

    private String password;

    private Date updated;

    private Date created;
}

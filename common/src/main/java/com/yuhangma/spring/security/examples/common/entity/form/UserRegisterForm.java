package com.yuhangma.spring.security.examples.common.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author Moore
 * @since 2019-08-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterForm {

    @NotEmpty
    @Length(min = 4, max = 16)
    private String username;

    @NotEmpty
    @Length(min = 4, max = 16)
    private String password;
}

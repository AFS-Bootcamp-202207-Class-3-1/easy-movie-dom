package com.oocl.easymovie.dto;

import lombok.*;

import java.util.Date;

/**
 * @author edward
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserResponse {

    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private String gender;
    private Date birthday;
    private String avatar;
    private Date createTime;
    private Date updateTime;

}

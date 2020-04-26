package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")}
)
public class MsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    @Column(length = 30)
    private String username;

    @Column(length = 30)
    private String nickname;

    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private String role;
    
}

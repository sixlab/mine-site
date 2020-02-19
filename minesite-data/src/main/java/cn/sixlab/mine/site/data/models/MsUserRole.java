package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "roleId"),
                @Index(columnList = "username")
        }
)
public class MsUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    @Column(length = 30)
    private String username;

    private Integer roleId;

    private Integer userId;
}

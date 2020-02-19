package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        indexes = {
                @Index(columnList = "roleId"),
                @Index(columnList = "authId")
        }
)
public class MsRoleAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    private Integer roleId;

    private Integer authId;
}

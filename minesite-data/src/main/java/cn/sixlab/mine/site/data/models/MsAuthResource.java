package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        indexes = {
                @Index(columnList = "type"),
                @Index(columnList = "authId"),
                @Index(columnList = "resourceId")
        }
)
public class MsAuthResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    // menu   菜单
    // button 按钮
    @Column(length = 10)
    private String type;

    private Integer authId;

    private Integer resourceId;
}

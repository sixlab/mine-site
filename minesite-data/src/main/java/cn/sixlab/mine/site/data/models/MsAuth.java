package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = "authCode")}
)
public class MsAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    private Integer weight;

    @Column(length = 30)
    private String groupName;

    @Column(length = 30)
    private String authCode;

    @Column(length = 30)
    private String authName;

    @Column(length = 200)
    private String authIntro;

}

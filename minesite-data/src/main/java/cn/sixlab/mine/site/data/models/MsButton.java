package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class MsButton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    @Column(length = 30)
    private String buttonName;

    @Column(length = 30)
    private String buttonIcon;

    @Column(length = 200)
    private String buttonIntro;
}

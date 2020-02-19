package cn.sixlab.mine.site.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(
        indexes = {
                @Index(columnList = "menuLevel")
        }
)
public class MsMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private Date createTime;

    private Integer weight;

    @Column(length = 30)
    private String menuName;

    @Column(length = 100)
    private String menuPath;

    private Integer menuLevel;

    private Boolean isLeaf;

    private Integer parentId;

    @Column(length = 30)
    private String menuIcon;

    @Column(length = 200)
    private String menuIntro;
}

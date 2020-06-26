package com.liuscoding.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Instructor
 * </p>
 *
 * @author Li Chen
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("edu_teacher")
@ApiModel(value="Instructor Object", description="Instructor")
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Instructor ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "Instructor Name")
    private String name;

    @ApiModelProperty(value = "Instructor Intro")
    private String intro;

    @ApiModelProperty(value = "Instructor Past Experience")
    private String career;

    @ApiModelProperty(value = "Level 1:Senior Instructor 2:Chief Instructor")
    private Integer level;

    @ApiModelProperty(value = "Instructor Avatar")
    private String avatar;

    @ApiModelProperty(value = "Sort")
    private Integer sort;

    @ApiModelProperty(value = "is Deleted 1（true）Deleted， 0（false）Not deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "TimeCreated")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "TimeUpdated")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

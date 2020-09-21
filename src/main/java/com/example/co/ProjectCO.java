package com.example.co;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjiameng
 * @date 2020-08-20 14:17
 */
@Data
@ApiModel(value = "项目管理实体")
public class ProjectCO {
    // id
    // 表字段 : tfb_project.id
    @ApiModelProperty(name = "id", position = 0, value = "项目编号[添加操作可不传递,修改必传]",
            extensions = {
                    @Extension(name = "hiddenList", properties = {
                            @ExtensionProperty(name = "/project/insert", value = "true")
                    }),
                    @Extension(name = "requireList", properties = {
                            @ExtensionProperty(name = "/project/update", value = "true"),
                    })
            })
//    @Id
    private Integer id;

    // 项目名称
    // 表字段 : tfb_project.name
    @ApiModelProperty(name = "name", value = "项目名称", required = true, position = 1)
    private String name;

    // 省
    // 表字段 : tfb_project.province
    @ApiModelProperty(name = "province", value = "省份", required = true, position = 2, example = "湖南省")
    private String province;

    // 市
    // 表字段 : tfb_project.city
    @ApiModelProperty(name = "city", value = "城市", required = true, position = 3, example = "长沙市")
    private String city;

    // 区
    // 表字段 : tfb_project.district
    @ApiModelProperty(name = "district", value = "地区", required = true, position = 4, example = "雨花区")
    private String district;

    // 开发商
    // 表字段 : tfb_project.owner
    @ApiModelProperty(name = "owner", value = "开发商", position = 6, required = true, allowableValues = "人名")
    private String owner;

    // 合作开始时间
    // 表字段 : tfb_project.startdate
    @ApiModelProperty(name = "startdate", value = "合作开始时间", position = 7, required = true, allowableValues = "yyyy-MM-dd HH:mm:ss")
    private Date startdate;

    // 合作结束时间
    // 表字段 : tfb_project.enddate
    @ApiModelProperty(name = "enddate", value = "合作结束时间", position = 8, required = true, allowableValues = "yyyy-MM-dd HH:mm:ss")
    private Date enddate;

    // 项目电话
    // 表字段 : tfb_project.tel
    @ApiModelProperty(name = "tel", value = "合作方手机号码", position = 9, required = true)
    private String tel;

}

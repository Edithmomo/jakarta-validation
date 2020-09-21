package com.example.controller;

import com.example.co.ProjectCO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author wangjiameng
 * @date 2020-08-20 14:20
 */
@Api(tags = {"项目管理"})
@Slf4j
@RestController
@RequestMapping("project")
public class ProjectController {

    @ApiOperation(value = "添加项目")
    @GetMapping("insert")
    public ProjectCO add(ProjectCO record) {
        try {
            Random random = new Random();
            record.setId(random.nextInt(100000));
            ObjectMapper mapper = new ObjectMapper();
            log.info("添加项目：{}", mapper.writeValueAsString(record));
            return record;
        } catch (Exception e) {
            log.info("添加项目失败:", e);
            return null;
        }
    }

    @ApiOperation(value = "修改项目")
    @GetMapping("update")
    public ProjectCO update(ProjectCO record) {
        try {
            log.info("id:{}", record.getId());
            ObjectMapper mapper = new ObjectMapper();
            log.info("修改项目：{}", mapper.writeValueAsString(record));
            return record;
        } catch (Exception e) {
            log.info("修改项目失败:", e);
            return null;
        }
    }
}

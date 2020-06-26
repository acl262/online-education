package com.liuscoding.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.licoding.commonutils.vo.ResultVo;
import com.liuscoding.edu.entity.Instructor;
import com.liuscoding.edu.model.query.InstructorQuery;
import com.liuscoding.edu.model.query.TeacherQuery;
import com.liuscoding.edu.service.InstructorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Instructor Controller
 * </p>
 *
 * @author Li Chen
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/edu/instructor")
@Api(tags = "instructorManagement")
public class InstructorController {

    /**
     * Inject instructorService
     */
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * @return List<Instructor>
     */
    @ApiOperation("queryAllInstructors")
    @GetMapping("/findAll")
    public ResultVo findAll(){
        List<Instructor> instructorList = instructorService.list(null);
        return ResultVo.ok().data("items", instructorList);
    }


    /**
     * @param id
     * @return ResultVo
     */
    @DeleteMapping("/{id}")
    @ApiOperation("DeleteInstructorById")
    public ResultVo deleteInstructor(@ApiParam(name = "id",required = true) @PathVariable String id){
        boolean result = instructorService.removeById(id);
        if(result){
           return ResultVo.ok();
        } else {
           return ResultVo.error();
        }
    }

    @ApiOperation("pageQueryInstructorWithoutCondition")
    @GetMapping("/pageInstructor/{page}/{size}")
    public ResultVo pageInstructor(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size){

        //all data is encapsulated into instructorPage object
        IPage<Instructor> instructorPage = new Page<>(page, size);
        instructorService.page(instructorPage, null);

        Map<String,Object> map = new HashMap<>(16);

        map.put("total", instructorPage.getTotal());
        map.put("rows", instructorPage.getRecords());

        return ResultVo.ok().data(map);
    }


    /**
     * @param page the current page
     * @param size  the size of each page
     * @return   ResultVo
     */
    @ApiOperation("pageQueryInstructorWithCondition")
    @PostMapping("/pageInstructorCondition/{page}/{size}")
    public ResultVo pageTeacherCondition(@ApiParam(value = "page",defaultValue = "1") @PathVariable("page") int page,
                                         @ApiParam(value = "size",defaultValue = "10")@PathVariable("size") int size,
                                         @RequestBody InstructorQuery instructorQuery){

        IPage<Instructor> pageInstructor = new Page<>(page,size);

        //conditions
        LambdaQueryWrapper<Instructor> query = new LambdaQueryWrapper<>();
        query.like(StringUtils.isNotBlank(instructorQuery.getName()), Instructor::getName,instructorQuery.getName());
        query.eq(Objects.nonNull(instructorQuery.getLevel()), Instructor::getLevel,instructorQuery.getLevel());
        query.ge(StringUtils.isNotBlank(instructorQuery.getBegin()), Instructor::getGmtCreate,instructorQuery.getBegin());
        query.le(StringUtils.isNotBlank(instructorQuery.getEnd()), Instructor::getGmtCreate,instructorQuery.getEnd());
        query.orderByDesc(Instructor::getGmtCreate);
        instructorService.page(pageInstructor, query);
        Map<String,Object> map = new HashMap<>(16);
        map.put("total", pageInstructor.getTotal());
        map.put("rows", pageInstructor.getRecords());
        return ResultVo.ok().data(map);
    }

    @GetMapping("/getInstructor/{id}")
    @ApiOperation("queryInstructorbyId")
    public ResultVo getTeacher(@ApiParam(value = "id", required = true) @PathVariable("id") String id){
        Instructor instructor = instructorService.getById(id);
        return ResultVo.ok().data("instructor", instructor);
    }

    @ApiOperation("updateInstructorbyId")
    @PutMapping("/updateInstructor/{id}")
    public ResultVo updateTeacher(@PathVariable String id, @RequestBody Instructor instructor){
        Instructor result = instructorService.getById(id);
        BeanUtils.copyProperties(instructor, result);
        result.setId(id);
        boolean updateResult = instructorService.updateById(result);
        if(updateResult){
            return ResultVo.ok();
        }else {
            return ResultVo.error();
        }
    }

    /**
     * @param instructor
     * @return ResultVo
     */
    @ApiOperation("addInstructor")
    @PostMapping("addInstructor")
    public ResultVo addInstructor(@RequestBody Instructor instructor){
        boolean save = instructorService.save(instructor);
        if(save){
            return ResultVo.ok();
        }
        return ResultVo.error();
    }
}


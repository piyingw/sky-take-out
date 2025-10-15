package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品");
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
    @GetMapping("/page")
    public Result<PageResult> page( DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    @DeleteMapping
    public Result delete(@RequestParam  List<Long>ids) {
        log.info("删除菜品,{}",ids);
        dishService.delete(ids);
        return Result.success();
    }
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品");
        dishService.update(dishDTO);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品信息,{}",id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }
}

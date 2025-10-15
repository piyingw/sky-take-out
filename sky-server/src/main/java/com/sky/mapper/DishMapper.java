package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    List<Dish> idQuery(List<Long> ids);

    @Delete("delete from dish where id = #{id}")
    void deleteByid(Long id);

    void delete(List<Long> ids);

    /**
     * 根据id查询菜品和口味
     *
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getByIdWithFlavor(Long id);
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
}

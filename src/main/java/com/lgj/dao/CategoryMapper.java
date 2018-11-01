package com.lgj.dao;

import com.lgj.entity.Category;
import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Category record);

    Category selectByPrimaryKey(Integer cid);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);
}
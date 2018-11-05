package com.lgj.dao;

import com.lgj.entity.Pet;
import java.util.List;

public interface PetMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Pet record);

    Pet selectByPrimaryKey(Integer pid);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    Pet findByStatus(String pstatus);
}
package com.ztx.world.base.mapper;

import com.ztx.world.base.entity.Constant;
import com.ztx.world.base.entity.ConstantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConstantMapper {
    int countByExample(ConstantExample example);

    int deleteByExample(ConstantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Constant record);

    int insertSelective(Constant record);

    List<Constant> selectByExample(ConstantExample example);

    Constant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Constant record, @Param("example") ConstantExample example);

    int updateByExample(@Param("record") Constant record, @Param("example") ConstantExample example);

    int updateByPrimaryKeySelective(Constant record);

    int updateByPrimaryKey(Constant record);
}
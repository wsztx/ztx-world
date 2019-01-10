package com.ztx.world.system.mapper;

import com.ztx.world.system.entity.Log;
import com.ztx.world.system.entity.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    int countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}
package z.z.config.mapper;


import org.apache.ibatis.annotations.Select;

import java.util.List;

// @Mapper
public interface HiMapper {

    @Select("select * from hi")
    List selectAll();
}

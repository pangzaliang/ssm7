package z.z.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface HiMapper {

    @Select("select * from hi")
    List selectAll();
}

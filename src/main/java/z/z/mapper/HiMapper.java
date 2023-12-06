package z.z.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HiMapper {

    @Select("select * from hi")
    List<Map<String, Object>> selectAll();
}

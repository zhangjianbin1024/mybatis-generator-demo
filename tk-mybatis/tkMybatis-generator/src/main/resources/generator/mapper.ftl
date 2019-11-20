package ${package};

import ${tableClass.fullClassName};
import com.myke.tk.base.CrudMapper;

/**
* 通用 Mapper 代码生成器
* base:com.myke.com.myke.tk.base.CrudMapper
* base:tk.mybatis.mapper.common.Mapper
* @author mapper-generator
*/
public interface ${tableClass.shortClassName}${mapperSuffix} extends ${baseMapper!"CrudMapper"}<${tableClass.shortClassName}> {

}





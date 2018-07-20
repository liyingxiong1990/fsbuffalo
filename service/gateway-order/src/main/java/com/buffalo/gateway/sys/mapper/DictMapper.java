
package com.buffalo.gateway.sys.mapper;

import com.buffalo.gateway.sys.model.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictMapper {


    public List<Dict> list();

    public List<Dict> getListByDictType(String dict_type);

    public void add(Dict dict);

    public void update(Dict dict);

    public void delete(int dict_id);
}

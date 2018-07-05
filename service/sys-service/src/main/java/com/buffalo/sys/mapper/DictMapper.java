
package com.buffalo.sys.mapper;

import java.util.List;
import com.buffalo.sys.model.Dict;
import org.springframework.stereotype.Repository;

@Repository
public interface DictMapper {


    public List<Dict> list();

    public List<Dict> getListByDictType(String dict_type);

    public void add(Dict dict);

    public void update(Dict dict);

    public void delete(int dict_id);
}

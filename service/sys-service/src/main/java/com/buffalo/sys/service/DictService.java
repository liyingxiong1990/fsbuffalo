
package com.buffalo.sys.service;

import java.util.List;
import com.buffalo.sys.model.Dict;

public interface DictService {
    public List<Dict> getAll();

    public List<Dict> getListByDictType(String dict_type);

    public void add(Dict dict) throws Exception;

    public void update(Dict dict) throws Exception;

    public void delete(int dict_type) throws Exception;
}

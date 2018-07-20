
package com.buffalo.gateway.sys.service;

import com.buffalo.gateway.sys.model.Dict;

import java.util.List;

public interface DictService {
    public List<Dict> getAll();

    public List<Dict> getListByDictType(String dict_type);

    public void add(Dict dict) throws Exception;

    public void update(Dict dict) throws Exception;

    public void delete(int dict_type) throws Exception;
}

package com.gajava.library.service;

import com.gajava.library.model.Base;

public interface CrudService <Entity extends Base> {
    Entity create(Entity entity);

    Entity findById(Long id);

    void delete(Long id);


}

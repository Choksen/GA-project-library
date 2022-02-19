package com.gajava.library.service;

import com.gajava.library.model.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<Entity extends Base> {
    Entity save(Entity entity);

    Entity findById(Long id);

    void delete(Long id);

    Page<Entity> findAll(Pageable pageable);


}

package com.gajava.library.service;

import com.gajava.library.model.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The base service of crud operations
 *
 * @param <Entity> inherited entity
 */
public interface CrudService<Entity extends Base> {
    /**
     * save entity
     *
     * @param entity entity
     * @return saved entity
     */
    Entity save(Entity entity);

    /**
     * update entity with id
     *
     * @param entity entity for update
     * @return updated entity
     */
    Entity update(Entity entity);

    /**
     * find entity by id
     *
     * @param id entity id
     * @return entity
     */
    Entity findById(Long id);

    /**
     * delete entity by id
     *
     * @param id entity id
     */
    void delete(Long id);

    /**
     * find all entities
     *
     * @param pageable pagination
     * @return page of entities
     */
    Page<Entity> findAll(Pageable pageable);


}

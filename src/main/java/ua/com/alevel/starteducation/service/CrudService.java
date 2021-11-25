package ua.com.alevel.starteducation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.model.BaseEntity;

import java.util.List;

public interface CrudService<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(Long id);
    boolean existById(Long id);
    E findById(Long id);
    Page findAll(Pageable pageable);


}

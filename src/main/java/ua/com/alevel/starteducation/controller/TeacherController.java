package ua.com.alevel.starteducation.controller;


import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.starteducation.dto.request.TeacherDtoRequest;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.dto.response.TeacherDtoResponse;
import ua.com.alevel.starteducation.facade.TeacherFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherFacade teacherFacade;

    public TeacherController(TeacherFacade teacherFacade) {
        this.teacherFacade = teacherFacade;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    private ResponseEntity<ResponseContainer<Boolean>> create(@Valid @RequestBody TeacherDtoRequest dto) {
        teacherFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(true));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    private ResponseEntity<ResponseContainer<Boolean>> update(@RequestBody TeacherDtoRequest dto, @PathVariable Long id) {
        teacherFacade.update(dto, id);
        return ResponseEntity.ok().body(new ResponseContainer<>(true));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseContainer<Boolean>> delete(@PathVariable Long id) {
        teacherFacade.delete(id);
        return ResponseEntity.ok().body(new ResponseContainer<>(true));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseContainer<TeacherDtoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ResponseContainer<>(teacherFacade.findById(id)));
    }

    @GetMapping()
    @PageableAsQueryParam
    private ResponseEntity<ResponseContainer<Page<TeacherDtoResponse>>> findAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok().body(new ResponseContainer<>(teacherFacade.findAll(pageable)));
    }
}

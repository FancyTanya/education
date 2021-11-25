package ua.com.alevel.starteducation.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.starteducation.dto.request.TopicDtoRequest;
import ua.com.alevel.starteducation.dto.response.ResponseContainer;
import ua.com.alevel.starteducation.dto.response.TopicDtoResponse;
import ua.com.alevel.starteducation.facade.TopicFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicFacade topicFacade;

    public TopicController(TopicFacade topicFacade) {
        this.topicFacade = topicFacade;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    private ResponseEntity<ResponseContainer<Boolean>> create(@Valid TopicDtoRequest dto, @RequestParam Long teacherId) {
        topicFacade.create(dto, teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseContainer<>(true));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    private ResponseEntity<ResponseContainer<Boolean>> update(TopicDtoRequest dto, @PathVariable Long id) {
        topicFacade.update(dto, id);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseContainer<Boolean>> delete(@PathVariable Long id) {
        topicFacade.delete(id);
        return ResponseEntity.ok(new ResponseContainer<>(true));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseContainer<TopicDtoResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseContainer<>(topicFacade.findById(id)));
    }

    @GetMapping()
    @PageableAsQueryParam
    private ResponseEntity<ResponseContainer<Page<TopicDtoResponse>>> findAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(new ResponseContainer<>(topicFacade.findAll(pageable)));
    }

    @GetMapping("/teacher")
    @PageableAsQueryParam
    private ResponseEntity<ResponseContainer<Page<TopicDtoResponse>>> findAllByTeacher(@RequestParam Long teacherId, @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(new ResponseContainer<>(topicFacade.findAllByTeacher(teacherId, pageable)));
    }
}

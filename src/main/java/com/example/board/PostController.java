package com.example.board;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse create(@RequestBody PostRequest request) {
        return postService.create(request);
    }

    @GetMapping
    public List<PostResponse> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponse findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public PostResponse updateById(@PathVariable("id") Long id, @RequestBody PostRequest request) {
        return postService.update(id, request);
    }
}

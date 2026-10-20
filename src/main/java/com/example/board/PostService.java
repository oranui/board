package com.example.board;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse create(PostRequest request) {
        return PostResponse.from(postRepository.save(new Post(request.title(), request.content())));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        return PostResponse.from(findPost(id));
    }

    public PostResponse update(Long id, PostRequest request) {
        Post post = findPost(id);
        post.update(request.title(), request.content());
        return PostResponse.from(post);
    }

    public void delete(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "본 게시물을 찾을 수 없습니다."));
    }
}

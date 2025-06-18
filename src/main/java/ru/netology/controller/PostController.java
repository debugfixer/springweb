package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Post>> all() {
    List<Post> posts = service.all();
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable long id) {
    try {
      Post post = service.getById(id);
      return ResponseEntity.ok(post);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("{\"error\": \"Post not found\"}");
    }
  }

  @PostMapping
  public ResponseEntity<Post> save(@RequestBody Post post) {
    Post saved = service.save(post);
    return ResponseEntity.ok(saved);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> removeById(@PathVariable long id) {
    try {
      service.removeById(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("{\"error\": \"Post not found\"}");
    }
  }
}

package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
  private final Map<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = idCounter.incrementAndGet();
      post.setId(id);
      posts.put(id, post);
    } else {
      if (!posts.containsKey(post.getId())) {
        throw new NotFoundException("Post with id=" + post.getId() + " not found");
      }
      posts.put(post.getId(), post);
    }
    return post;
  }

  public void removeById(long id) {
    if (!posts.containsKey(id)) {
      throw new NotFoundException("Post with id=" + id + " not found");
    }
    posts.remove(id);
  }
}

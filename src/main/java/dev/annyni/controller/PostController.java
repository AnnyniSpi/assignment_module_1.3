package dev.annyni.controller;

import dev.annyni.model.Label;
import dev.annyni.model.Post;
import dev.annyni.model.Status;
import dev.annyni.repository.PostRepository;
import java.util.List;

public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(Long postId){
        return postRepository.findById(postId);
    }

    public Post addLabelToPost(Long postId, Label label){
        Post post = getPostById(postId);
        post.addLabel(label);

        return postRepository.update(post);
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post createPost(String title, String content, List<Label> labels, Status status){
        Post post = new Post(title, content, labels, status);

        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String title, String content, List<Label> labels, Status status){
        Post post = new Post(title, content, labels, status);

        post.setId(postId);

        return postRepository.update(post);
    }

    public void deletePostById(Long postId){
        postRepository.deleteById(postId);
    }
}

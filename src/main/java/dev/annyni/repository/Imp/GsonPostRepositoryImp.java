package dev.annyni.repository.Imp;

import dev.annyni.model.Post;
import dev.annyni.model.Status;
import dev.annyni.repository.LabelRepository;
import dev.annyni.repository.PostRepository;
import java.io.File;
import java.util.List;

public class GsonPostRepositoryImp implements PostRepository {

    private final File file = new File("src/main/resources/posts.json");
    private final DataImp dataImp = new DataImp(file);
    private final LabelRepository labelRepository = new GsonLabelRepositoryImp();

    @Override
    public Post findById(Long postId) {
        return dataImp.readData(Post.class).stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Post с таким id = " + postId + " не существует"));
    }

    @Override
    public List<Post> findAll() {
        return dataImp.readData(Post.class);
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = dataImp.readData(Post.class);
        Long postId = dataImp.generateId(posts, Post::getId);
        post.setId(postId);
        posts.add(post);
        dataImp.writeData(posts);

        return post;
    }

    @Override
    public void deleteById(Long postId) {
        List<Post> posts = dataImp.readData(Post.class);
        posts.stream()
                .filter(post -> post.getId().equals(postId))
                .forEach(post -> post.setStatus(Status.DELETED));

        dataImp.writeData(posts);
    }

    @Override
    public Post update(Post updatePost) {
        List<Post> posts = dataImp.readData(Post.class);
        posts.stream()
                .filter(post -> post.getId().equals(updatePost.getId()))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(updatePost.getTitle());
                    post.setContent(updatePost.getContent());
                    post.setLabels(updatePost.getLabels());
                    post.setStatus(updatePost.getStatus());

                    updatePost.getLabels().forEach(labelRepository::update);
                    updatePost.getLabels().forEach(labelRepository::save);
                });

        dataImp.writeData(posts);

        return updatePost;
    }
}

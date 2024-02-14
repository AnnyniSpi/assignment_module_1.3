package dev.annyni.controller;

import dev.annyni.model.Post;
import dev.annyni.model.Status;
import dev.annyni.model.Writer;
import dev.annyni.repository.WriterRepository;

import java.util.List;

public class WriterController {

    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer getWriterById(Long writerId){
        return writerRepository.findById(writerId);
    }

    public Writer addPostToWriter(Long writerId, Post post){
        Writer writer = getWriterById(writerId);
        writer.addPost(post);

        return writerRepository.update(writer);
    }

    public List<Writer> getAllWriter(){
        return writerRepository.findAll();
    }

    public Writer createWriter(String firstName, String lastName, List<Post> posts, Status status){
        Writer writer = new Writer(firstName, lastName, posts, status);
        return writerRepository.save(writer);
    }

    public Writer udateWriter(Long writerId, String firstName, String lastName, List<Post> posts, Status status){
        Writer writer = new Writer(firstName, lastName, posts, status);
        writer.setId(writerId);

        return writerRepository.update(writer);
    }

    public void deleteWriterById(Long writerId){
       writerRepository.deleteById(writerId);
    }
}

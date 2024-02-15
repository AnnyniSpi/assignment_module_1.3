package dev.annyni.repository.Imp;

import dev.annyni.model.Status;
import dev.annyni.model.Writer;
import dev.annyni.repository.PostRepository;
import dev.annyni.repository.WriterRepository;
import java.io.File;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {

    private final File file = new File("src/main/resources/writers.json");
    private final DataImp dataImp = new DataImp(file);
    private final PostRepository postRepository = new GsonPostRepositoryImp();

    @Override
    public Writer findById(Long writerId) {
        return dataImp.readData(Writer.class).stream()
                .filter(writer -> writer.getId().equals(writerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Writer с таким id = " + writerId + " не существует"));
    }

    @Override
    public List<Writer> findAll() {
        return dataImp.readData(Writer.class);
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> writers = dataImp.readData(Writer.class);
        Long writerId = dataImp.generateId(writers, Writer::getId);
        writer.setId(writerId);
        writers.add(writer);
        dataImp.writeData(writers);

        return writer;
    }

    @Override
    public void deleteById(Long writerId) {
        List<Writer> writers = dataImp.readData(Writer.class);
        writers.stream()
                .filter(writer -> writer.getId().equals(writerId))
                .forEach(writer -> writer.setStatus(Status.DELETED));

        dataImp.writeData(writers);
    }

    @Override
    public Writer update(Writer updateWriter) {
        List<Writer> writers = dataImp.readData(Writer.class);
        writers.stream()
                .filter(writer -> writer.getId().equals(updateWriter.getId()))
                .findFirst()
                .ifPresent(writer -> {
                    writer.setFirstName(updateWriter.getFirstName());
                    writer.setLastName(updateWriter.getLastName());
                    writer.setPosts(updateWriter.getPosts());
                    writer.setStatus(updateWriter.getStatus());

                    updateWriter.getPosts().forEach(postRepository::update);
                    updateWriter.getPosts().forEach(postRepository::save);

                });

        return updateWriter;
    }
}

package dev.annyni.repository.Imp;

import dev.annyni.model.Label;
import dev.annyni.model.Status;
import dev.annyni.repository.LabelRepository;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class GsonLabelRepositoryImp implements LabelRepository {

    private final File file = new File("src/main/resources/labels.json");
    private final DataImp dataImp = new DataImp(file);

    @Override
    public Label findById(Long labelId) {
        return dataImp.readData(Label.class).stream()
                .filter(label -> Objects.equals(label.getId(), labelId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Label с таким id = " + labelId + " не существует"));
    }

    @Override
    public List<Label> findAll() {
        return dataImp.readData(Label.class);
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = dataImp.readData(Label.class);
        labels.add(label);
        dataImp.writeData(labels);

        return label;
    }

    @Override
    public void deleteById(Long labelId) {
        List<Label> labels = dataImp.readData(Label.class);
        labels.stream()
                .filter(label -> Objects.equals(label.getId(), labelId))
                .forEach(label -> label.setStatus(Status.DELETED));
        dataImp.writeData(labels);
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = dataImp.readData(Label.class);
        labels.replaceAll(newLabel ->
                newLabel.getId().equals(label.getId()) ? label : newLabel
        );

        dataImp.writeData(labels);

        return label;
    }
}

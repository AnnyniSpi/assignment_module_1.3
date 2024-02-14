package dev.annyni.controller;

import dev.annyni.model.Label;
import dev.annyni.model.Status;
import dev.annyni.repository.LabelRepository;
import java.util.List;

public class LabelController {

    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label getLabelById(Long labelId){
        return labelRepository.findById(labelId);
    }

    public List<Label> getAllLabels(){
        return labelRepository.findAll();
    }

    public Label createLabel(String name, Status status){
        Label label = new Label(name, status);
        return labelRepository.save(label);
    }

    public void deleteLabelById(Long labelId){
        labelRepository.deleteById(labelId);
    }

    public Label updateLabel(Long labelId, String name, Status status){
        Label label = new Label(name, status);
        label.setId(labelId);

        return labelRepository.update(label);
    }
}

package dev.annyni;

import dev.annyni.controller.LabelController;
import dev.annyni.controller.PostController;
import dev.annyni.controller.WriterController;
import dev.annyni.model.Post;
import dev.annyni.repository.Imp.GsonLabelRepositoryImp;
import dev.annyni.repository.Imp.GsonPostRepositoryImp;
import dev.annyni.repository.Imp.GsonWriterRepositoryImpl;
import dev.annyni.repository.LabelRepository;
import dev.annyni.repository.PostRepository;
import dev.annyni.repository.WriterRepository;
import dev.annyni.view.LabelView;
import dev.annyni.view.PostView;
import dev.annyni.view.WriterView;

public class Main {

    public static void main(String[] args) {
//        LabelRepository labelRepository = new GsonLabelRepositoryImp();
//        LabelController labelController = new LabelController(labelRepository);
//        LabelView labelView = new LabelView(labelController);
//
//        labelView.printMenu();

//        PostRepository postRepository = new GsonPostRepositoryImp();
//        PostController postController = new PostController(postRepository);
//        PostView postView = new PostView(postController);
//
//        postView.printMenu();

        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        WriterController writerController = new WriterController(writerRepository);
        WriterView writerView = new WriterView(writerController);

        writerView.printMenu();

    }
}

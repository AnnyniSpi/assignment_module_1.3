package dev.annyni.view;

import dev.annyni.controller.LabelController;
import dev.annyni.controller.PostController;
import dev.annyni.controller.WriterController;
import dev.annyni.repository.Imp.GsonLabelRepositoryImp;
import dev.annyni.repository.Imp.GsonPostRepositoryImp;
import dev.annyni.repository.Imp.GsonWriterRepositoryImpl;
import dev.annyni.repository.LabelRepository;
import dev.annyni.repository.PostRepository;
import dev.annyni.repository.WriterRepository;

import java.util.Scanner;

public class MainView {

    Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println("Выберите: ");
        System.out.println("1. Label");
        System.out.println("2. Post");
        System.out.println("3. Writer");
        System.out.println("4. Выйти");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                runLabel();
                break;
            case 2:
                runPost();
                break;
            case 3:
                runWriter();
            case 4:
                System.out.println("Выход");
                return;
            default:
                System.out.println("Вы ввели неверный символ!");
                break;
        }
    }

    public void runLabel(){
        LabelRepository labelRepository = new GsonLabelRepositoryImp();
        LabelController controller = new LabelController(labelRepository);
        LabelView labelView = new LabelView(controller);
        labelView.printMenu();
    }

    public void runPost(){
        PostRepository postRepository = new GsonPostRepositoryImp();
        PostController controller = new PostController(postRepository);
        PostView postView = new PostView(controller);
        postView.printMenu();
    }


    public void runWriter(){
        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        WriterController controller = new WriterController(writerRepository);
        WriterView writeView = new WriterView(controller);
        writeView.printMenu();
    }
}

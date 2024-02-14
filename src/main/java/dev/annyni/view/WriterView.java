package dev.annyni.view;

import dev.annyni.controller.WriterController;
import dev.annyni.model.Label;
import dev.annyni.model.Post;
import dev.annyni.model.Status;
import dev.annyni.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView {

    private final Scanner scanner;
    private final WriterController controller;

    public WriterView(WriterController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void printMenu(){

        while (true){
            System.out.println("Что бы вы хотели? Введите число.");
            System.out.println("1. Создать Writer");
            System.out.println("2. Изменить Writer");
            System.out.println("3. Найти Writer по id");
            System.out.println("4. Найти все Writers");
            System.out.println("5. Удалить Writer");
            System.out.println("6. Добавить Post в Writer");
            System.out.println("7. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    createWriter();
                    break;
                case 2:
                    updateWriter();
                    break;
                case 3:
                    getWriter();
                    break;
                case 4:
                    getAllWriters();
                    break;
                case 5:
                    deleteWriter();
                    break;
                case 6:
                    addPost();
                    break;
                case 7:
                    System.out.println("Выход из главного меню");
                    return;
                default:
                    System.out.println("Вы ввели неверный символ!");
                    break;
            }
        }

    }

    private void addPost() {
        System.out.println("Введите id Writer в который хотите добавить Post: ");

        Long writerId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введите title Post: ");
        String title = scanner.nextLine();
        System.out.println("Введите title Post: ");
        String content = scanner.nextLine();
        List<Label> labels = new ArrayList<>();
        Status status = Status.ACTIVE;

        Post post = new Post(title, content, labels, status);

        Writer writer = controller.addPostToWriter(writerId, post);

        System.out.println("Post успешно добален в Writer: " + writer);
    }

    private void getAllWriters() {
        System.out.println("Представлены все Writer: ");
        List<Writer> writers = controller.getAllWriter();
        for (Writer writer : writers) {
            System.out.println(writer);
        }
    }

    private void deleteWriter() {
        System.out.println("Введите id Writer который хотите удалить: ");

        Long writerId = scanner.nextLong();
        controller.deleteWriterById(writerId);

        System.out.println("Writer удален!");
    }

    private void getWriter() {
        System.out.println("Есть: ");
        getAllWriters();
        System.out.println("Введите id Writer которого вы хотите вывести: ");

        Long writerId = scanner.nextLong();

        Writer writer = controller.getWriterById(writerId);

        System.out.println("Writer успешно найден! " + writer);

    }

    private void updateWriter() {
        System.out.println("Есть: ");
        getAllWriters();
        System.out.println("Введите id Writer которого вы хотите изменить: ");

        Long writerId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Введите новый firstName : ");
        String firstName = scanner.nextLine();
        System.out.println("Введите новый lastName: ");
        String lastName = scanner.nextLine();
        Status status = Status.ACTIVE;

        List<Post> posts = new ArrayList<>();
        Writer writer = controller.udateWriter(writerId, firstName, lastName, posts, status);

        System.out.println("Writer успешно изменен! " + writer);
    }

    private void createWriter() {
        System.out.println("Введите firstName : ");
        String firstName = scanner.nextLine();
        System.out.println("Введите lastName: ");
        String lastName = scanner.nextLine();
        Status status = Status.ACTIVE;

        List<Post> posts = new ArrayList<>();

        Writer writer = controller.createWriter(firstName, lastName, posts, status);

        System.out.println("Writer успешно создан. " + writer);
    }
}

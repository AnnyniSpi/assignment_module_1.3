package dev.annyni.view;

import dev.annyni.controller.PostController;
import dev.annyni.model.Label;
import dev.annyni.model.Post;
import dev.annyni.model.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {

    private final Scanner scanner;
    private final PostController controller;

    public PostView(PostController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void printMenu(){

        while (true){
            System.out.println("Что бы вы хотели? Введите число.");
            System.out.println("1. Создать Post");
            System.out.println("2. Изменить Post");
            System.out.println("3. Найти Post по id");
            System.out.println("4. Найти все Posts");
            System.out.println("5. Удалить Post");
            System.out.println("6. Добавить Label в Post");
            System.out.println("7. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    createPost();
                    break;
                case 2:
                    updatePost();
                    break;
                case 3:
                    getPost();
                    break;
                case 4:
                    getAllPosts();
                    break;
                case 5:
                    deletePost();
                    break;
                case 6:
                    addLabel();
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

    private void addLabel() {
        System.out.println("Введите id Post в который хотите добавить Label: ");

        Long postId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введите имя Label: ");
        String name = scanner.nextLine();
        Status status = Status.ACTIVE;
        Label label = new Label(name, status);

        Post post = controller.addLabelToPost(postId, label);

        System.out.println("Label успешно добален в Post: " + post);
    }

    private void getAllPosts() {
        System.out.println("Представлены все Posts: ");
        List<Post> posts = controller.getAllPost();
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    private void deletePost() {
        System.out.println("Введите id Post который хотите удалить: ");

        Long postId = scanner.nextLong();
        controller.deletePostById(postId);

        System.out.println("Post удален!");
    }

    private void getPost() {
        System.out.println("Есть: ");
        getAllPosts();
        System.out.println("Введите id Post которого вы хотите вывести: ");

        Long postId = scanner.nextLong();

        Post post = controller.getPostById(postId);

        System.out.println("Post успешно найден! " + post);

    }

    private void updatePost() {
        System.out.println("Есть: ");
        getAllPosts();
        System.out.println("Введите id Post которого вы хотите изменить: ");

        Long postId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Введите новый заголовок : ");
        String title = scanner.nextLine();
        System.out.println("Введите новый контент: ");
        String content = scanner.nextLine();
        Status status = Status.ACTIVE;

        List<Label> labels = new ArrayList<>();
        Post post = controller.updatePost(postId, title, content, labels, status);

        System.out.println("Post успешно изменен! " + post);
    }

    private void createPost() {
        System.out.println("Введите заголовок: ");
        String title = scanner.nextLine();
        System.out.println("Введите контент: ");
        String content = scanner.nextLine();
        Status status = Status.ACTIVE;

        List<Label> labels = new ArrayList<>();

        Post post = controller.createPost(title, content, labels, status);

        System.out.println("Post успешно создан. " + post);
    }
}

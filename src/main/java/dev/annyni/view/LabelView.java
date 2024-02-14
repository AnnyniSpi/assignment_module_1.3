package dev.annyni.view;

import dev.annyni.controller.LabelController;
import dev.annyni.model.Label;
import dev.annyni.model.Status;
import java.util.List;
import java.util.Scanner;

public class LabelView {

    private final Scanner scanner;
    private final LabelController controller;

    public LabelView(LabelController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void printMenu(){

        while (true){
            System.out.println("Что бы вы хотели? Введите число.");
            System.out.println("1. Создать Label");
            System.out.println("2. Изменить Label");
            System.out.println("3. Найти Label по id");
            System.out.println("4. Найти все Label");
            System.out.println("5. Удалить Label");
            System.out.println("6. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    createLabel();
                    break;
                case 2:
                    updateLabel();
                    break;
                case 3:
                    getLabel();
                    break;
                case 4:
                    getAllLabels();
                    break;
                case 5:
                    deleteLabel();
                    break;
                case 6:
                    System.out.println("Выход из главного меню");
                    return;
                default:
                    System.out.println("Вы ввели неверный символ!");
                    break;
            }
        }

    }

    private void getAllLabels() {
        System.out.println("Представлены все Labels: ");
        List<Label> labels = controller.getAllLabels();
        for (Label label : labels) {
            System.out.println(label);
        }
    }

    private void deleteLabel() {
        System.out.println("Введите id Label который хотите удалить: ");

        Long labelId = scanner.nextLong();
        controller.deleteLabelById(labelId);

        System.out.println("label удален!");
    }

    private void getLabel() {
        System.out.println("Есть: ");
        getAllLabels();
        System.out.println("Введите id Label которого вы хотите вывести: ");

        Long labelId = scanner.nextLong();

        Label label = controller.getLabelById(labelId);

        System.out.println("Label успешно найден! " + label);

    }

    private void updateLabel() {
        System.out.println("Есть: ");
        getAllLabels();
        System.out.println("Введите id Label имя которого вы хотите изменить: ");

        Long labelId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Введите новое имя: ");
        String name = scanner.nextLine();
        Status status = Status.ACTIVE;

        Label label = controller.updateLabel(labelId, name, status);

        System.out.println("Label успешно изменен! " + label);
    }

    private void createLabel() {
        System.out.println("Введите имя: ");

        String name = scanner.nextLine();
        Status status = Status.ACTIVE;

        Label label = controller.createLabel(name, status);

        System.out.println("Label успешно создан. " + label);
    }


}

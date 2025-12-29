package View.GUI;

import Service.Controller;
import View.Interpreter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FirstWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        List<Controller> controllers = Interpreter.controllers;
        List<String> programs_strings = Interpreter.programs_strings;

        primaryStage.setTitle("Select a Program");

        ListView<String> programList = new ListView<>();

        programList.setItems(FXCollections.observableArrayList(programs_strings));

        Button selectButton = new Button("Select");
        selectButton.setOnAction(e -> {
            int index = programList.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Controller selectedController = controllers.get(index);

                SecondWindow secondWindow = new SecondWindow(selectedController);
                Stage secondStage = new Stage();
                try {
                    secondWindow.start(secondStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        VBox layout = new VBox(10, programList, selectButton);
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
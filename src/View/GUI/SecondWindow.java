package View.GUI;

import Model.Other.PrgState;
import Model.Value.StringValue;
import Model.Value.Value;
import Service.Controller;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class SecondWindow extends Application {

    private int idSelected;

    private Controller controller;

    private TextField nrPrgStatesField;
    private TableView heapTable;
    private ListView outList;
    private ListView fileTableList;
    private ListView prgStateIdList;
    private TableView symTable;
    private ListView exeStackList;
    private Button runOneStepButton;

    public SecondWindow(Controller ctrl) {
        this.controller = ctrl;
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        // Row 0: Number of PrgStates + Run button
        nrPrgStatesField = new TextField();

        runOneStepButton = new Button("Run One Step");
        runOneStepButton.setOnAction(e -> {
            try {
                runOneStep();
            }
            catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        grid.add(new Label("Nr PrgStates:"), 0, 0);
        grid.add(nrPrgStatesField, 1, 0);
        grid.add(runOneStepButton, 2, 0);

        // Row 1-2: Heap and Out
        heapTable = new TableView();
        outList = new ListView();
        grid.add(new Label("Heap:"), 0, 1);
        grid.add(heapTable, 0, 2);
        grid.add(new Label("Out:"), 1, 1);
        grid.add(outList, 1, 2);

        // Row 3-4: FileTable and PrgState IDs
        fileTableList = new ListView();
        prgStateIdList = new ListView();

        prgStateIdList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                idSelected = (Integer) newValue;
                updateForState();
            }
        });

        grid.add(new Label("FileTable:"), 0, 3);
        grid.add(fileTableList, 0, 4);
        grid.add(new Label("PrgState IDs:"), 1, 3);
        grid.add(prgStateIdList, 1, 4);

        // Row 5-6: SymTable and ExeStack
        symTable = new TableView();
        exeStackList = new ListView();
        grid.add(new Label("SymTable:"), 0, 5);
        grid.add(symTable, 0, 6);
        grid.add(new Label("ExeStack:"), 1, 5);
        grid.add(exeStackList, 1, 6);

        TableColumn<Pair<String, String>, String> addressCol2 = new TableColumn<>("variable name");
        TableColumn<Pair<String, String>, String> valueCol2 = new TableColumn<>("Value");

        addressCol2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));
        valueCol2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue()));

        symTable.getColumns().addAll(addressCol2, valueCol2);

        TableColumn<Pair<Integer, String>, Integer> addressCol = new TableColumn<>("Address");
        TableColumn<Pair<Integer, String>, String> valueCol = new TableColumn<>("Value");

        addressCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getKey()).asObject());
        valueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue()));

        heapTable.getColumns().addAll(addressCol, valueCol);

        Scene scene = new Scene(grid, 1000, 700);
        stage.setScene(scene);
        stage.show();

        populateUI();
    }

    private void runOneStep() throws Exception {
        controller.oneStepForAllPrgNoInput();
        populateUI();
    }

    private void populateUI() {

        List<PrgState> states = controller.getPrgList();

        nrPrgStatesField.setText(String.valueOf(states.size()));
        outList.getItems().clear();
        outList.getItems().addAll(states.get(0).getOut().fileToString2());
        outList.getItems().remove(0);

        fileTableList.getItems().clear();
        fileTableList.getItems().addAll(states.get(0).getFileTable().fileToString2());
        fileTableList.getItems().remove(0);

        prgStateIdList.getItems().clear();
        for (PrgState it : states) {
            prgStateIdList.getItems().add(it.getIdOfState());
        }

        for (PrgState it : states) {
            if (it.getIdOfState() == idSelected) {
                heapTable.getItems().clear();
                for (Map.Entry<Integer, Value> entry : it.getHeap().getContent().entrySet()) {
                    heapTable.getItems().add(new Pair<>(entry.getKey(), entry.getValue().toString()));
                }
                symTable.getItems().clear();
                for (Map.Entry<String, Value> entry : it.getSymTable().getContent().entrySet()) {
                    symTable.getItems().add(new Pair<>(entry.getKey(), entry.getValue().toString()));
                }
                exeStackList.getItems().clear();
                exeStackList.getItems().addAll(it.getExeStack().fileToString2());
                exeStackList.getItems().remove(0);
            }
        }

    }

    private void updateForState() {
        for (PrgState it : controller.getPrgList()) {
            if (it.getIdOfState() == idSelected) {
                symTable.getItems().clear();
                for (Map.Entry<String, Value> entry : it.getSymTable().getContent().entrySet()) {
                    symTable.getItems().add(new Pair<>(entry.getKey(), entry.getValue().toString()));
                }
                exeStackList.getItems().clear();
                exeStackList.getItems().addAll(it.getExeStack().fileToString2());
            }
        }
    }
}
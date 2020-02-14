package ru.kiril.delete.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kiril.delete.CmdHandler;
import ru.kiril.delete.Notification;

public class ControllerMain {
    public TextField ip;
    public ComboBox discList;
    public TextField fileOUT;
    public HBox panelHboxTool;
    ObservableList<String> AZList = FXCollections.observableArrayList();
    public static final Logger logger = LogManager.getLogger();

    public void initialize() {

        for (int i = 65; i <= 90; i++) {
            AZList.add(String.valueOf((char) i));
        }

        discList.setItems(AZList);

    }

    public void delete(ActionEvent actionEvent) {
        String[] ipm = ip.getText().split(",");
        for (String s :
                ipm) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    CmdHandler cmdHandler = new CmdHandler();
                    try {
                        cmdHandler.cmdRun(s, discList.getValue().toString().toLowerCase(), fileOUT.getText());
                    } catch (Exception e) {
                        logger.error("удаление", e);
                        new Notification("",e.getMessage());
                    }
                }
            });
            thread.start();
        }
    }
}

package dk.base;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupEditCard {

    /** The popup is displayed when a card is click and is to be edited or deleted.
     *  @param  dayDisplay the cards parent.
     *  @param card the card clicked. */
    public static void display(DayDisplay dayDisplay, Card card){
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Edit card");

        // Create elements of popup window
        TextField textField = new TextField(card.getText());
        Button buttonSave= new Button("Save card");
        Button buttonDelete = new Button("Delete card.");

        //Set buttonSave action
        buttonSave.setOnAction(event -> {
            card.setText(textField.getText());
            popupWindow.close();
        });

        //Set buttonDelete action
        buttonDelete.setOnAction(event -> {
            dayDisplay.removeCard(card);
            popupWindow.close();
        });


        VBox vbox = new VBox(3);
        HBox hbox = new HBox(3);

        hbox.getChildren().addAll(buttonSave, buttonDelete);
        vbox.getChildren().addAll(textField, hbox);

        Scene scene = new Scene(vbox, 300, 60);

        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }
}

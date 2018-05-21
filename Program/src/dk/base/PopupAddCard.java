package dk.base;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

public class PopupAddCard {

    private Card returnCard = null;


    public static void display(DayDisplay dayDisplay){
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add card to " + dayDisplay.getDisplayName());

        TextField textField = new TextField("Enter your todo.");
        Button button1= new Button("Add card");

        button1.setOnAction(e -> {
            dayDisplay.addCard(createCard(textField.getText(), dayDisplay));
            popupWindow.close();
        });



        //popupWindow.addEventHandler(new EventHandler<KeyEvent>());


        /*
        final EventHandler<KeyEvent> keyEventHandler =
        new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    setPressed(keyEvent.getEventType()
                        == KeyEvent.KEY_PRESSED);

                    keyEvent.consume();
                }
            }
        };
         */

        VBox layout = new VBox(3);

        layout.getChildren().addAll(textField, button1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 60);

        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    private static Card createCard(String todoText, DayDisplay dayDisplay){
        return new Card(todoText, dayDisplay);
    }
}

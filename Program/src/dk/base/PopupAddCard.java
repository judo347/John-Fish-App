package dk.base;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupAddCard {

    /** The popup displayed when a new card is to be added.
     *  @param dayDisplay the display to receive the created card. */
    public static void display(DayDisplay dayDisplay){

        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add card to " + dayDisplay.getDisplayName());

        // Create elements of popup window
        TextField textField = new TextField("Enter your todo.");
        Button button1= new Button("Add card");

        // Button action: create the card and add it to the display
        button1.setOnAction(e -> {
            dayDisplay.addCard(createCard(textField.getText(), dayDisplay));
            popupWindow.close();
        });

        VBox layout = new VBox(3);

        layout.getChildren().addAll(textField, button1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 60);

        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    /** Creates a card.
     *  @param dayDisplay the cards parent display.
     *  @param todoText  the text of the card.
     *  @return the card created from the given parameters. */
    private static Card createCard(String todoText, DayDisplay dayDisplay){
        return new Card(todoText, dayDisplay);
    }
}

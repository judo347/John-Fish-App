package dk.base;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupAddCard {

    private Card returnCard = null;


    public static void display(DayDisplay dayDisplay){
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Add card to " + dayDisplay.getDisplayName());


        //Label label1= new Label("Pop up window now displayed");
        TextField textField = new TextField("Enter your todo.");
        Button button1= new Button("Add card");

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

    private static Card createCard(String todoText, DayDisplay dayDisplay){

        return new Card(todoText, dayDisplay);
    }
}

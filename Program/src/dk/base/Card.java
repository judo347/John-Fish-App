package dk.base;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Card extends HBox {

    public Card(String inputString) {
        super();

        this.setBackground(new Background(new BackgroundFill(new Color(160/255f, 160/255f, 160/255f, 1), null, null)));
        this.setPadding(new Insets(2)); //TODO does not give space between cards
        CheckBox checkbox = new CheckBox();
        HBox left = new HBox();
        left.getChildren().add(checkbox);

        Text text = new Text(inputString);
        HBox right = new HBox();
        //right.setMaxWidth(0.1);
        right.getChildren().add(text);

        this.getChildren().addAll(left, right);
    }
}

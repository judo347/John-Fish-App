package dk.base;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Card extends HBox {

    public Card(String inputString) {
        super();

        setMouseActions();

        //this.setPadding(new Insets(10)); //TODO Should be done with css functions TODO does not give space between cards
        setStyleNormal(); //Set style of this class
        CheckBox checkbox = new CheckBox();
        HBox left = new HBox();
        left.getChildren().add(checkbox);

        Text text = new Text(inputString);
        HBox right = new HBox();
        //right.setMaxWidth(0.1);
        right.getChildren().add(text);

        this.getChildren().addAll(left, right);
    }

    private void setMouseActions(){

        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Card.this.setStyleFaded(); //TODO TAKE NOTE OF THIS AKA REMEMBER!
                System.out.println("Detected");
            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Released Mouse");
            }
        });
    }

    private void setStyleNormal(){

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-background-color: grey;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
    }

    private void setStyleFaded(){ //TODO Change to something that looks faded

        this.setStyle(  "-fx-padding: 3;" + //Inside: space between border and content
                "-fx-background-color: red;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 3;" +
                "-fx-border-insets: 9;" + //Outside: space to other elements
                "-fx-border-radius: 5;" + //Side of rounding in border
                "-fx-border-color: black;" +
                "-fx-hgap: 50;" +
                "-fx-vgap: 50;");
    }
}

package dk.base;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //Create root
        Pane root = new HBox();

        //Create days
        DayDisplay old = new DayDisplay("Old");
        DayDisplay today = new DayDisplay("Today");
        DayDisplay tomorrow = new DayDisplay("Tomorrow");
        DayDisplay theDayAfter = new DayDisplay("The Day After");
        DayDisplay thoughts = new DayDisplay("Thoughts"); //TODO Might just get reworked to a textfield? Or something else?

        //Add the HBox' to the root
        root.getChildren().addAll(old, today, tomorrow, theDayAfter, thoughts);

        //Set the stage
        Scene scene = new Scene(root, 1280, 620);
        stage.setScene(scene);
        stage.setTitle("John Fish App.");
        stage.show();
    }
}

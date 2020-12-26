package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image img;
        img = new Image("/view/fxml/img/icon.png");
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/Login.fxml"));
        primaryStage.setTitle("Barber Management");
        primaryStage.setScene(new Scene(root, 577, 548));
        primaryStage.getIcons().add(img);
        primaryStage.setMinHeight(548);
        primaryStage.setMinWidth(577);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

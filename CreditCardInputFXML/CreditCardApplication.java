package CreditCardInputFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CreditCardApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditCardInput.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // Load Controller Class
        stage.setTitle("FXML Credit Card Validator");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}









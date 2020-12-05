package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("vues/Main.fxml"));
			
			Scene scene = new Scene(root,1280,720);
			
			// Set the title of the window
			primaryStage.setTitle("PharmaCERI");

			// Set a favicon to the window
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ressources/icon.png")));

			scene.getStylesheets().add(getClass().getResource("vues/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			mainStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

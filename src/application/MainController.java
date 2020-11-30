package application;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MainController {
	
    @FXML
    private Text content;

    @FXML 
    private void handleButton1() {
    	content.setText("saluce hombre!");
        System.out.println("saluce hombre!");
    } 
	
}

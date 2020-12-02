package application.models.UI;

import application.models.Abstracts.Pharmacie;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PharmacieCell extends ListCell<Pharmacie> {
	
    private HBox content;
    private Text name;
    private Text type;

    public PharmacieCell() {
        super();
        
        name = new Text();
        type = new Text();
        
        VBox vBox = new VBox(name, type);
        
        content = new HBox(new Label("[Graphic]"), vBox);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Pharmacie item, boolean empty) {
        super.updateItem(item, empty);
        
        if (item != null && !empty) {
        	
            name.setText(item.getNom());
            type.setText(String.format("%d", item.getType()));
            setGraphic(content);
        }
        else {
            setGraphic(null);
        }
    }

}

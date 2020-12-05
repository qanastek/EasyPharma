package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import application.models.CarteBancaireClassique;
import application.models.CompteClassique;
import application.models.CompteFranchisé;
import application.models.DBTransaction;
import application.models.Employé;
import application.models.MasterCard;
import application.models.Pays;
import application.models.Personne;
import application.models.PharmacieFranchisée;
import application.models.PharmacieIndépendante;
import application.models.PharmacienDiplômé;
import application.models.ProduitPharmaceutique;
import application.models.Transaction;
import application.models.Visa;
import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.Client;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.PaysFactory;
import application.models.Abstracts.Pharmacie;
import application.models.Enums.TypeProduitPharmaceutique;
import application.models.Patterns.CommandTransaction.CommandTransaction;
import application.models.UI.PharmacieCell;
import application.models.Utils.Toolbox;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

public class MainController implements Initializable {
    
    /**
     * Side menu of pharmacy
     */
	@FXML
	private Text typePharmacieMain;
    
    // The current pharmacy
    public static Pharmacie currentPharmacie;

    /**
     * Side menu of pharmacy
     */
    ObservableList<Pharmacie> data = FXCollections.observableArrayList(
		new PharmacieIndépendante("Grande Pharmacie Grégoire",340,"50917660800016", PaysFactory.getInstance().getPays("france")),
		new PharmacieFranchisée("Pharmacie Paris Charonne",1200,"80360531000017", PaysFactory.getInstance().getPays("france"))
    );
    @FXML
    private ListView<Pharmacie> sideMenu;
	@FXML
	private TextField nomPharma;	
	@FXML
	private TextField siretPharma;	
	@FXML
	private TextField paysPharma;	
	@FXML
	private TextField surfacePharma;	
	@FXML
	private TextField responsablePharma;
	@FXML
	private Text compteClassique;
	@FXML
	private Text comptePharmacie;

    /**
     * Cartes
     */
    public static ObservableList<CarteBancaire> dataCartes = FXCollections.observableArrayList();
    @FXML
    private TableView<CarteBancaire> cartes;
    @FXML
    private TableColumn<CarteBancaire, String> réseauCarte;
    @FXML
    private TableColumn<CarteBancaire, String> numéroCarte;
    public static CarteBancaire currentSelectedCard = null;

    /**
     * Employés
     */
    ObservableList<Employé> dataEmployés = FXCollections.observableArrayList();  
    @FXML
    private TableView<Employé> employees;
    @FXML
    private TableColumn<Employé, String> employésNom;
    @FXML
    private TableColumn<Employé, String> employésPrenom;
    @FXML
    private TableColumn<Employé, String> employésAddresse;
    @FXML
    private TableColumn<Employé, String> employésMétier;
    public static Employé currentSelectedEmployee = null;
    
    /**
     * Stock
     */
    ObservableList<ProduitPharmaceutique> dataStock = FXCollections.observableArrayList();
    @FXML
    private TableView<ProduitPharmaceutique> stock;
    @FXML
    private TableColumn<ProduitPharmaceutique, String> stockNom;
    @FXML
    private TableColumn<ProduitPharmaceutique, String> stockType;
    @FXML
    private TableColumn<ProduitPharmaceutique, String> stockPrixAchat;
    @FXML
    private TableColumn<ProduitPharmaceutique, String> stockPrixVente;
    public static ProduitPharmaceutique currentSelectedProduct= null;
    
    /**
     * Transaction
     */
    ObservableList<Transaction> dataTransaction = FXCollections.observableArrayList();    
    @FXML
    private TableView<Transaction> transactions;
    @FXML
    private TableColumn<Transaction, String> transactionsVendeur;
    @FXML
    private TableColumn<Transaction, String> transactionsAcheteur;
    @FXML
    private TableColumn<Transaction, String> transactionsDate;
    @FXML
    private TableColumn<Transaction, String> transactionsMontant;
    @FXML
    private TableColumn<Transaction, String> transactionsProduits;
    public static Transaction currentSelectedTransaction = null;
    
    /**
     * Franchisées
     */
    ObservableList<PharmacieFranchisée> dataFranchises = FXCollections.observableArrayList();
    @FXML
    private TableView<PharmacieFranchisée> franchises;
    @FXML
    private TableColumn<PharmacieFranchisée, String> franchisesNom;
    @FXML
    private TableColumn<PharmacieFranchisée, String> franchisesSiret;
    @FXML
    private TableColumn<PharmacieFranchisée, String> franchisesSurface;
    @FXML
    private TableColumn<PharmacieFranchisée, String> franchisesResponsable;
    @FXML
    private TableColumn<PharmacieFranchisée, String> franchisesNbrEmployes;
    public static PharmacieFranchisée currentSelectedFranchise = null;
    
    /**
     * Tabs
     */
    @FXML
    private Tab tabEnsemble;
    @FXML
    private Tab tabEmployés;
    @FXML
    private Tab tabStock;
    @FXML
    private Tab tabTransactions;
    @FXML
    private  Tab tabFranchisés;

    /**
     * Clients list
     */
    public static ObservableList<Client> clients = FXCollections.observableArrayList(
		new Personne("Lary", "Page", "Champ de Mars, 5 Avenue Anatole France, 75007 Paris"),
		new Personne("Mark", "Zuckerberg", "1600 Pennsylvania Avenue NW, Washington, DC 20500, États-Unis"),
		new PharmacieFranchisée("Pharmacie Eiffel",140,"12345678900000", PaysFactory.getInstance().getPays("france"))
	);
    
    /**
     * Initialize method for the controller
     */    
	public void initialize(URL location, ResourceBundle resources) {
		
		// Link the observable lists with the GUI
		linkObservables();
		
		listenClick();
		
		// Force client 1 to get a mastercard
		clients.get(0).addCarteBancaire(new CarteBancaireClassique(new MasterCard()));
	}
	
	/**
	 * Listen to the user click
	 */
	private void listenClick() {
		
		sideMenu.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// OnClick pharmacy
		sideMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pharmacie>() {
 
            public void changed(ObservableValue<? extends Pharmacie> observable, Pharmacie oldValue, Pharmacie newValue) {
            	
            	// Change the current
            	MainController.currentPharmacie = newValue;
            	
            	dataCartes.setAll(MainController.currentPharmacie.getCompteBancaire().getCartes());
            	
            	// Update the view            	
            	changeContent();
            }
        });

		// OnClick cards
		cartes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CarteBancaire>() {
 
            public void changed(ObservableValue<? extends CarteBancaire> observable, CarteBancaire oldValue, CarteBancaire newValue) {
            	
            	System.out.println(newValue);
            	
            	// Change the current
            	MainController.currentSelectedCard = newValue;
            }
        });

		// OnClick cards
		employees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employé>() {
 
            public void changed(ObservableValue<? extends Employé> observable, Employé oldValue, Employé newValue) {
            	
            	System.out.println(newValue);
            	
            	// Change the current
            	MainController.currentSelectedEmployee = newValue;
            }
        });

		// OnClick cards
		stock.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProduitPharmaceutique>() {
 
            public void changed(ObservableValue<? extends ProduitPharmaceutique> observable, ProduitPharmaceutique oldValue, ProduitPharmaceutique newValue) {
            	
            	System.out.println(newValue);
            	
            	// Change the current
            	MainController.currentSelectedProduct = newValue;
            }
        });

		// OnClick cards
		transactions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Transaction>() {
 
            public void changed(ObservableValue<? extends Transaction> observable, Transaction oldValue, Transaction newValue) {
            	
            	System.out.println(newValue);
            	
            	// Change the current
            	MainController.currentSelectedTransaction = newValue;
            }
        });
		
		// OnClick cards
		franchises.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PharmacieFranchisée>() {
			
			public void changed(ObservableValue<? extends PharmacieFranchisée> observable, PharmacieFranchisée oldValue, PharmacieFranchisée newValue) {
				
				System.out.println(newValue);
				
				// Change the current
				MainController.currentSelectedFranchise = newValue;
			}
		});
	}

	/**
	 * Link the observables list with the GUI
	 */
	private void linkObservables() {
				
		// Set the listview data
		sideMenu.setItems(data);
		
		// Set the card item
		cartes.setItems(dataCartes);
		réseauCarte.setCellValueFactory(new PropertyValueFactory("réseau"));
		numéroCarte.setCellValueFactory(new PropertyValueFactory("numéro"));
		
		// Set the employees item
		employees.setItems(dataEmployés);
		employésNom.setCellValueFactory(new PropertyValueFactory("nom"));
		employésPrenom.setCellValueFactory(new PropertyValueFactory("prénom"));
		employésAddresse.setCellValueFactory(new PropertyValueFactory("adresse"));
		employésMétier.setCellValueFactory(new PropertyValueFactory("Métier"));
		
		// Set the stock item
		stock.setItems(dataStock);
		stockNom.setCellValueFactory(new PropertyValueFactory("nom"));
		stockType.setCellValueFactory(new PropertyValueFactory("type"));
		stockPrixAchat.setCellValueFactory(new PropertyValueFactory("prixAchat"));
		stockPrixVente.setCellValueFactory(new PropertyValueFactory("prixVente"));
		
		// Set the transactions item
		transactions.setItems(dataTransaction);
		transactionsVendeur.setCellValueFactory(new PropertyValueFactory("vendeur"));
		transactionsAcheteur.setCellValueFactory(new PropertyValueFactory("acheteur"));
		transactionsDate.setCellValueFactory(new PropertyValueFactory("date"));
		transactionsMontant.setCellValueFactory(new PropertyValueFactory("montant"));
		transactionsProduits.setCellValueFactory(new PropertyValueFactory("produits"));
		
		// Set the franchises item
		franchises.setItems(dataFranchises);
		franchisesNom.setCellValueFactory(new PropertyValueFactory("nom"));	
		franchisesSiret.setCellValueFactory(new PropertyValueFactory("siret"));	
		franchisesSurface.setCellValueFactory(new PropertyValueFactory("surfaceCommerciale"));	
		franchisesResponsable.setCellValueFactory(new PropertyValueFactory("responsableName"));	
		franchisesNbrEmployes.setCellValueFactory(new PropertyValueFactory("nbrEmployés"));
	}

	/**
	 * Refresh the vue
	 */
	void changeContent() {
		
		typePharmacieMain.setText("Pharmacie " + MainController.currentPharmacie.getType());
		
    	nomPharma.setText(MainController.currentPharmacie.getNom());	
    	compteClassique.setText(
    			String.valueOf(MainController.currentPharmacie.getCompteBancaire().getSolde())
    			+ " €"
		);	
    	siretPharma.setText(MainController.currentPharmacie.getSiret().toString());		
    	paysPharma.setText(MainController.currentPharmacie.getPays().toString());		
    	surfacePharma.setText(String.valueOf(MainController.currentPharmacie.getSurfaceCommerciale()));		
    	
    	PharmacienDiplômé responsable = MainController.currentPharmacie.getResponsable();
    	responsablePharma.setText(responsable != null ? responsable.toString() : "Pas de responsable");		

    	dataFranchises.setAll(((PharmacieFranchisée) MainController.currentPharmacie).getAllPharmaciesFranchisées());
    	
    	boolean status = true;
    	
    	// Check if is a subsidiary
    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {
    		status = false;
        	comptePharmacie.setText(
        			String.valueOf(((PharmacieFranchisée) MainController.currentPharmacie).getCompteFranchisé().getSolde())
        			+ " €"
			);	
		}
    	else {
        	comptePharmacie.setText("Aucun");    		
    	}
    	
    	// Hide / Show the tabs
		tabEnsemble.setDisable(false);
		tabEmployés.setDisable(false);
		tabFranchisés.setDisable(status);
		tabStock.setDisable(false);
		tabTransactions.setDisable(false);
		
		/**
		 * Update the observables for the new instance
		 */
		dataCartes.setAll(MainController.currentPharmacie.getCompteBancaire().getCartes());
		dataStock.setAll(MainController.currentPharmacie.getStock());
		dataEmployés.setAll(MainController.currentPharmacie.getEmployés());
		dataTransaction.setAll(DBTransaction.getInstance().getAll(
				MainController.currentPharmacie
		));
    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {

        	// Add the credit card to the pharmacy
        	((PharmacieFranchisée) MainController.currentPharmacie).removeFranchisé(
        			MainController.currentSelectedFranchise
			);
	    }
	}
    
    @FXML 
    private void addCard() {
    	
    	// Check empty
    	if (MainController.currentPharmacie == null) return;
    	
    	System.out.println("Add card");
    	
    	CarteBancaire cb = new CarteBancaireClassique(new Visa());

    	// Add the credit card to the pharmacy
    	MainController.currentPharmacie.getCompteBancaire().addCarte(cb);
    	
    	// Set cards
    	dataCartes.setAll(MainController.currentPharmacie.getCompteBancaire().getCartes());

    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delCard() {
		
		// Check empty
		if (MainController.currentSelectedCard == null) return;
		
		System.out.println("del card");
		
		// Add the credit card to the pharmacy
		MainController.currentPharmacie.getCompteBancaire().removeCarte(currentSelectedCard);
		
		// Clear card
		MainController.currentSelectedCard = null;
		
		// Set cards
		dataCartes.setAll(MainController.currentPharmacie.getCompteBancaire().getCartes());
		
		// Update the view            	
		changeContent();	
	}
    
    @FXML 
    private void addEmployee() {
    	
    	// Check empty
    	if (MainController.currentPharmacie == null) return;
    	
    	System.out.println("Add employee");
    	
    	Employé e = new PharmacienDiplômé("Jack", "Ma", "China", new Date(), 2000.0);

    	// Add the credit card to the pharmacy
    	MainController.currentPharmacie.addEmployé(e);
    	
    	// Set employees
    	dataEmployés.setAll(MainController.currentPharmacie.getEmployés());

    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delEmployee() {
		
		// Check empty
		if (MainController.currentSelectedEmployee == null) return;
		
		System.out.println("del employee");
		
		// Add the credit card to the pharmacy
		MainController.currentPharmacie.removeEmployé(
				MainController.currentSelectedEmployee
		);
		
		// Clear card
		MainController.currentSelectedEmployee = null;

    	// Set employees
    	dataEmployés.setAll(MainController.currentPharmacie.getEmployés());
    	
		// Update the view            	
		changeContent();	
	}
    
    @FXML 
    private void addStock() {
    	
    	// Check empty
    	if (MainController.currentPharmacie == null) return;
    	
    	System.out.println("Add item");
    	
    	ProduitPharmaceutique p = new ProduitPharmaceutique(
			"Doliprane",
			TypeProduitPharmaceutique.Médicament,
			2.0,
			0.1,
			new Date()
		);

    	// Add the credit card to the pharmacy
    	MainController.currentPharmacie.addProduit(p);
    	
    	// TODO: Problème synchronisation ave cle stock

    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delStock() {
		
		// Check empty
		if (MainController.currentSelectedProduct == null) return;
		
		System.out.println("del item");
		
		// Add the credit card to the pharmacy
		MainController.currentPharmacie.removeProduit(
				MainController.currentSelectedProduct
		);
		
		// Clear card
		MainController.currentSelectedProduct = null;
		
		// Update the view            	
		changeContent();
	}
    
    @FXML 
    private void addFranchise() {
    	
    	// Check empty
    	if (MainController.currentPharmacie == null) return;
    	
    	System.out.println("Add franchise");
    	
    	PharmacieFranchisée p = new PharmacieFranchisée(
    		"Pharmacie " + String.valueOf(Toolbox.generateInt()),
    		Toolbox.generateInt(),
			"54fsd584f4s8f",
			PaysFactory.getInstance().getPays("france")
		);

    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {
    		
    		System.out.println("Franchisé");

        	// Add the credit card to the pharmacy
        	((PharmacieFranchisée) MainController.currentPharmacie).addFranchisé(p);
    	
	    	// Set cards
	    	dataFranchises.setAll(((PharmacieFranchisée) MainController.currentPharmacie).getAllPharmaciesFranchisées());
	    	data.add(p);
	    	clients.add(p);
		}
    	
    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delFranchise() {
		
		// Check empty
		if (MainController.currentSelectedFranchise == null) return;
		
		System.out.println("del franchise");
		
    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {

        	// Add the credit card to the pharmacy
        	((PharmacieFranchisée) MainController.currentPharmacie).removeFranchisé(
        			MainController.currentSelectedFranchise
			);
        	
        	clients.remove(MainController.currentSelectedFranchise);
        	data.remove(MainController.currentSelectedFranchise);
	    }    	
    	
		// Clear card
		MainController.currentSelectedFranchise = null;

    	// Set cards
    	dataFranchises.setAll(((PharmacieFranchisée) MainController.currentPharmacie).getAllPharmaciesFranchisées());
				
		// Update the view            	
		changeContent();	
	}

    @FXML 
    private void ajoutePharmacie() {

        System.out.println("saluce add!");
        
		data.add(
			new PharmacieIndépendante(
				"Pharmacie " + (int) (Math.random() * (99999 - 1)),
				100,
				String.valueOf((int) (Math.random() * (9999999 - 99999))),
				PaysFactory.getInstance().getPays("france")
			)
		);
		
		for (Pharmacie pharmacie : data) {
			System.out.println(pharmacie.getType());
		}
    }	
    
    @FXML 
    private void sellStock() {
		
		// Check empty
		if (MainController.currentSelectedProduct == null) return;
    	
    	System.out.println("Sell product!");

        Pair<Client,CarteBancaire> res = askSell(MainController.currentSelectedProduct);
    	
        // Check not cancelled
        if (res != null) {
        	
        	Client client = res.getKey();
        	
        	// Vendre
        	CommandTransaction cmd = MainController.currentPharmacie.vendre(
    			new ArrayList<ProduitPharmaceutique>(Arrays.asList(
					MainController.currentSelectedProduct
				)),
    			client,
    			client.getCompteBancaire().getCartes().indexOf(res.getValue())
    		);
        	
        	// Ajouter la commande au client
        	client.ajouterCommande(cmd);
            
        	// Remove the selected product from the stock
        	this.delStock();

    		dataTransaction.setAll(DBTransaction.getInstance().getAll(
				MainController.currentPharmacie
    		));
    		
    		System.out.println(dataTransaction);
		}
    }
    
    /**
     * Selling modal
     * @param Product
     * @return
     */
    private Pair<Client, CarteBancaire> askSell(ProduitPharmaceutique p) {

    	System.out.println("MainController.clients");
    	System.out.println(MainController.clients);
    	
    	// Create the custom dialog.
    	Dialog<Pair<Client, CarteBancaire>> dialog = new Dialog<>();
    	dialog.setTitle("Enregistrement de vente");
    	
    	// Set the button types.
    	ButtonType validateBtnType = new ButtonType("Valider", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(validateBtnType, ButtonType.CANCEL);

    	// Create the username and password labels and fields.
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));

    	// Choice box for the client
    	ChoiceBox<Client> client = new ChoiceBox<Client>(MainController.clients);

    	// Observable list of cards
    	ObservableList<CarteBancaire> cbs =  FXCollections.observableArrayList();

		// Choice box for the client credit card
    	ChoiceBox<CarteBancaire> cbClient = new ChoiceBox<CarteBancaire>(cbs);
    	
    	grid.add(new Label("Client:"), 0, 0);
    	grid.add(client, 1, 0);
    	grid.add(new Label("Carte bleu client:"), 0, 1);
    	grid.add(cbClient, 1, 1);

    	// Enable/Disable the balidation button
    	Node validateBtn = dialog.getDialogPane().lookupButton(validateBtnType);
    	validateBtn.setDisable(true);

    	// Check if the client was registered
    	client.valueProperty().addListener((observable, oldValue, newValue) -> {
    		
    	    validateBtn.setDisable(newValue == null);
    	    
    	    if (newValue != null) {
        	    cbs.setAll(client.getValue().getCompteBancaire().getCartes());
        	    cbClient.setDisable(false);
			}
    	});
    	
    	// Check if the credit card was registered
    	cbClient.valueProperty().addListener((observable, oldValue, newValue) -> {
    	    validateBtn.setDisable(newValue == null);
    	});

    	dialog.getDialogPane().setContent(grid);

    	// Request focus on the username field by default.
    	Platform.runLater(() -> client.requestFocus());

    	// Convert the result to a username-password-pair when the login button is clicked.
    	dialog.setResultConverter(dialogButton -> {
    	    if (dialogButton == validateBtnType) {
    	        return new Pair<>(client.getValue(), cbClient.getValue());
    	    }
    	    return null;
    	});

    	Optional<Pair<Client, CarteBancaire>> result = dialog.showAndWait();

    	result.ifPresent(sellContent -> {
    	    System.out.println("Client=" + sellContent.getKey() + ", Carte bancaire=" + sellContent.getValue());
    	});
    	
    	System.out.println("ici");

    	// Check cancelled
    	if (client.getValue() != null && cbClient.getValue() != null) {
            return new Pair<>(client.getValue(), cbClient.getValue());			
		}
    	else {
    		return null;
    	}
    }
}

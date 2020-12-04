package application.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import application.models.CarteBancaireClassique;
import application.models.CompteClassique;
import application.models.CompteFranchisé;
import application.models.DBTransaction;
import application.models.Employé;
import application.models.Pays;
import application.models.PharmacieFranchisée;
import application.models.PharmacieIndépendante;
import application.models.PharmacienDiplômé;
import application.models.ProduitPharmaceutique;
import application.models.Transaction;
import application.models.Visa;
import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.PaysFactory;
import application.models.Abstracts.Pharmacie;
import application.models.Enums.TypeProduitPharmaceutique;
import application.models.UI.PharmacieCell;
import application.models.Utils.Toolbox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class MainController implements Initializable {
	
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
     * Initialize method for the controller
     */    
	public void initialize(URL location, ResourceBundle resources) {
		
		// Link the observable lists with the GUI
		linkObservables();
		
		listenClick();
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
		
//		// Set the stock item
//		stock.setItems(MainController.currentPharmacie.getStock());
//		stockNom.setCellValueFactory(new PropertyValueFactory("nom"));
//		stockType.setCellValueFactory(new PropertyValueFactory("type"));
//		stockPrixAchat.setCellValueFactory(new PropertyValueFactory("prixAchat"));
//		stockPrixVente.setCellValueFactory(new PropertyValueFactory("prixVente"));
		
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
    	siretPharma.setText(MainController.currentPharmacie.getSiret().toString());		
    	paysPharma.setText(MainController.currentPharmacie.getPays().toString());		
    	surfacePharma.setText(String.valueOf(MainController.currentPharmacie.getSurfaceCommerciale()));		
    	
    	PharmacienDiplômé responsable = MainController.currentPharmacie.getResponsable();
    	responsablePharma.setText(responsable != null ? responsable.toString() : "Pas de responsable");		
    	
    	boolean status = true;
    	
    	// Check if is a subsidiary
    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {
    		status = false;
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
		}
    	
    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delFranchise() {
		
		// Check empty
		if (MainController.currentSelectedCard == null) return;
		
		System.out.println("del franchise");
		
    	if (MainController.currentPharmacie instanceof PharmacieFranchisée) {

        	// Add the credit card to the pharmacy
        	((PharmacieFranchisée) MainController.currentPharmacie).removeFranchisé(
        			MainController.currentSelectedFranchise
			);
	    }
    	
		// Clear card
		MainController.currentSelectedFranchise = null;
				
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
}

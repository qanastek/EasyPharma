package application.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import application.models.CarteBancaireClassique;
import application.models.CompteClassique;
import application.models.CompteFranchis�;
import application.models.DBTransaction;
import application.models.Employ�;
import application.models.Pays;
import application.models.PharmacieFranchis�e;
import application.models.PharmacieInd�pendante;
import application.models.PharmacienDipl�m�;
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
		new PharmacieInd�pendante("Grande Pharmacie Gr�goire",340,"50917660800016", PaysFactory.getInstance().getPays("france")),
		new PharmacieFranchis�e("Pharmacie Paris Charonne",1200,"80360531000017", PaysFactory.getInstance().getPays("france"))
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
    private TableColumn<CarteBancaire, String> r�seauCarte;
    @FXML
    private TableColumn<CarteBancaire, String> num�roCarte;
    public static CarteBancaire currentSelectedCard = null;

    /**
     * Employ�s
     */
    ObservableList<Employ�> dataEmploy�s = FXCollections.observableArrayList();  
    @FXML
    private TableView<Employ�> employees;
    @FXML
    private TableColumn<Employ�, String> employ�sNom;
    @FXML
    private TableColumn<Employ�, String> employ�sPrenom;
    @FXML
    private TableColumn<Employ�, String> employ�sAddresse;
    @FXML
    private TableColumn<Employ�, String> employ�sM�tier;
    public static Employ� currentSelectedEmployee = null;
    
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
     * Franchis�es
     */
    ObservableList<PharmacieFranchis�e> dataFranchises = FXCollections.observableArrayList();
    @FXML
    private TableView<PharmacieFranchis�e> franchises;
    @FXML
    private TableColumn<PharmacieFranchis�e, String> franchisesNom;
    @FXML
    private TableColumn<PharmacieFranchis�e, String> franchisesSiret;
    @FXML
    private TableColumn<PharmacieFranchis�e, String> franchisesSurface;
    @FXML
    private TableColumn<PharmacieFranchis�e, String> franchisesResponsable;
    @FXML
    private TableColumn<PharmacieFranchis�e, String> franchisesNbrEmployes;
    public static PharmacieFranchis�e currentSelectedFranchise = null;
    
    /**
     * Tabs
     */
    @FXML
    private Tab tabEnsemble;
    @FXML
    private Tab tabEmploy�s;
    @FXML
    private Tab tabStock;
    @FXML
    private Tab tabTransactions;
    @FXML
    private  Tab tabFranchis�s;
    
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
		employees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employ�>() {
 
            public void changed(ObservableValue<? extends Employ�> observable, Employ� oldValue, Employ� newValue) {
            	
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
		franchises.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PharmacieFranchis�e>() {
			
			public void changed(ObservableValue<? extends PharmacieFranchis�e> observable, PharmacieFranchis�e oldValue, PharmacieFranchis�e newValue) {
				
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
		r�seauCarte.setCellValueFactory(new PropertyValueFactory("r�seau"));
		num�roCarte.setCellValueFactory(new PropertyValueFactory("num�ro"));
		
		// Set the employees item
		employees.setItems(dataEmploy�s);
		employ�sNom.setCellValueFactory(new PropertyValueFactory("nom"));
		employ�sPrenom.setCellValueFactory(new PropertyValueFactory("pr�nom"));
		employ�sAddresse.setCellValueFactory(new PropertyValueFactory("adresse"));
		employ�sM�tier.setCellValueFactory(new PropertyValueFactory("M�tier"));
		
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
		franchisesNbrEmployes.setCellValueFactory(new PropertyValueFactory("nbrEmploy�s"));
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
    	
    	PharmacienDipl�m� responsable = MainController.currentPharmacie.getResponsable();
    	responsablePharma.setText(responsable != null ? responsable.toString() : "Pas de responsable");		
    	
    	boolean status = true;
    	
    	// Check if is a subsidiary
    	if (MainController.currentPharmacie instanceof PharmacieFranchis�e) {
    		status = false;
		}
    	
    	// Hide / Show the tabs
		tabEnsemble.setDisable(false);
		tabEmploy�s.setDisable(false);
		tabFranchis�s.setDisable(status);
		tabStock.setDisable(false);
		tabTransactions.setDisable(false);
		
		/**
		 * Update the observables for the new instance
		 */
		dataCartes.setAll(MainController.currentPharmacie.getCompteBancaire().getCartes());
		dataEmploy�s.setAll(MainController.currentPharmacie.getEmploy�s());
		dataTransaction.setAll(DBTransaction.getInstance().getAll(
				MainController.currentPharmacie
		));
    	if (MainController.currentPharmacie instanceof PharmacieFranchis�e) {

        	// Add the credit card to the pharmacy
        	((PharmacieFranchis�e) MainController.currentPharmacie).removeFranchis�(
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
    	
    	Employ� e = new PharmacienDipl�m�("Jack", "Ma", "China", new Date(), 2000.0);

    	// Add the credit card to the pharmacy
    	MainController.currentPharmacie.addEmploy�(e);
    	
    	// Set employees
    	dataEmploy�s.setAll(MainController.currentPharmacie.getEmploy�s());

    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delEmployee() {
		
		// Check empty
		if (MainController.currentSelectedEmployee == null) return;
		
		System.out.println("del employee");
		
		// Add the credit card to the pharmacy
		MainController.currentPharmacie.removeEmploy�(
				MainController.currentSelectedEmployee
		);
		
		// Clear card
		MainController.currentSelectedEmployee = null;

    	// Set employees
    	dataEmploy�s.setAll(MainController.currentPharmacie.getEmploy�s());
    	
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
			TypeProduitPharmaceutique.M�dicament,
			2.0,
			0.1,
			new Date()
		);

    	// Add the credit card to the pharmacy
    	MainController.currentPharmacie.addProduit(p);
    	
    	// TODO: Probl�me synchronisation ave cle stock

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
    	
    	PharmacieFranchis�e p = new PharmacieFranchis�e(
    		"Pharmacie " + String.valueOf(Toolbox.generateInt()),
    		Toolbox.generateInt(),
			"54fsd584f4s8f",
			PaysFactory.getInstance().getPays("france")
		);

    	if (MainController.currentPharmacie instanceof PharmacieFranchis�e) {
    		
    		System.out.println("Franchis�");

        	// Add the credit card to the pharmacy
        	((PharmacieFranchis�e) MainController.currentPharmacie).addFranchis�(p);
    	
	    	// Set cards
	    	dataFranchises.setAll(((PharmacieFranchis�e) MainController.currentPharmacie).getAllPharmaciesFranchis�es());
		}
    	
    	// Update the view            	
    	changeContent();	
    }

	@FXML 
	private void delFranchise() {
		
		// Check empty
		if (MainController.currentSelectedCard == null) return;
		
		System.out.println("del franchise");
		
    	if (MainController.currentPharmacie instanceof PharmacieFranchis�e) {

        	// Add the credit card to the pharmacy
        	((PharmacieFranchis�e) MainController.currentPharmacie).removeFranchis�(
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
			new PharmacieInd�pendante(
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

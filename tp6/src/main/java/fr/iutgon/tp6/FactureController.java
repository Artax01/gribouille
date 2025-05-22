package fr.iutgon.tp6;

import fr.iutgon.tp6.modele.FabriqueProduits;
import fr.iutgon.tp6.modele.Ligne;
import fr.iutgon.tp6.modele.Produit;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FactureController implements Initializable {
  public TableView<Ligne> table;
  public TableColumn<Ligne, Integer> qte;
  public TableColumn<Ligne, Produit> produit;
  public TableColumn<Ligne, Number> prixUnitaire;
  public TableColumn<Ligne, Number> totalHT;
  public TableColumn<Ligne, Number> totalTTC;
  public TextField sommeFacture;

  /**
   Called to initialize a controller after its root element has been completely processed.

   @param location  The location used to resolve relative paths for the root object, or
   {@code null} if the location is not known.
   @param resources The resources used to localize the root object, or {@code null} if
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
	  qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
	    
	    produit.setCellValueFactory(param -> {
			return param.getValue().produitProperty();
			// bindings.select dans le futur au lieu de la ligne au dessus
	    });
	    
	    prixUnitaire.setCellValueFactory(param -> {
			return param.getValue().getProduit().prixProperty();
			// bindings.select dans le futur au lieu de la ligne au dessus
	    });
	    
	    totalHT.setCellValueFactory(param -> {
	    	return param.getValue().totalHTProperty();
	    	// bindings.select dans le futur au lieu de la ligne au dessus
	    });
	    
	    totalTTC.setCellValueFactory(param -> {
	    	return param.getValue().totalTTCProperty();
	    	// bindings.select dans le futur au lieu de la ligne au dessus
	    });
  }

  public void onAjouter(ActionEvent actionEvent) {
	  Random r = new Random();
	  Ligne ligne = new Ligne(r.nextInt(4) + 1, FabriqueProduits.getProduits().get(r.nextInt(FabriqueProduits.getProduits().size() - 1)));
	  // on exclu le "produit" promotion et on ne peut pas avoir une quantite nulle
	  table.getItems().add(ligne);
  }
}

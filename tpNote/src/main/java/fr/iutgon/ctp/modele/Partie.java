package fr.iutgon.ctp.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/** Décrit une partie */
public class Partie {
  private SimpleStringProperty mot = new SimpleStringProperty();
  private SimpleIntegerProperty erreurs = new SimpleIntegerProperty();

  /** Crée un descripteur avec un mot et un nombre d'erreurs */
  public Partie(String mot, int erreurs) {
    this.mot.set(mot);
    this.erreurs.set(erreurs);
  }

  /** Récupère le mot de la partie décrite */
  public String getMot() {
    return mot.get();
  }

  /** Récupère le score de la partie décrite */
  public int getErreurs() {
    return erreurs.get();
  }

  /** Le mot de la partie décrite */
  public SimpleStringProperty motProperty() {
    return mot;
  }

  /** Les erreurs de la partie décrite */
  public SimpleIntegerProperty erreursProperty() {
    return erreurs;
  }

} // class Partie

package fr.iutgon.ctp.modele;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Le modèle du pendu */
public class Pendu {
  private final ReadOnlyBooleanWrapper potence = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper mat = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper soutien = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper corde = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper tete = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper corps = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper bras = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper jambes = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper sourire = new ReadOnlyBooleanWrapper(true);
  private final ReadOnlyBooleanWrapper[] dessins = {potence, mat, soutien, tete, corps, bras, jambes, corde, sourire};
  private final SimpleIntegerProperty erreurs = new SimpleIntegerProperty(0);
  private final BooleanExpression perdu = erreurs.greaterThanOrEqualTo(dessins.length);
  private final List<SimpleBooleanProperty> trouvees = new ArrayList<>();
  private final List<StringExpression> lettres = new ArrayList<>();
  private final ObservableList<Character> proposees = FXCollections.observableArrayList();
  private final ObservableList<Character> disponibles = FXCollections.observableArrayList();
  private String mot;

  /** Propriété indiquant si les bras doivent être affichés */
  public BooleanExpression brasProperty() {
    return bras.getReadOnlyProperty();
  }

  /** Propriété indiquant si la corde doit être affichée */
  public BooleanExpression cordeProperty() {
    return corde.getReadOnlyProperty();
  }

  /** Propriété indiquant si le corps doit être affiché */
  public BooleanExpression corpsProperty() {
    return corps.getReadOnlyProperty();
  }

  /** Teste si la partie est gagnée */
  public boolean isGagne() {
    for (SimpleBooleanProperty trouvee : trouvees)
      if (!trouvee.get()) return false;
    // Aucune lettre est non-trouvée
    return true;
  }

  /** Retourne la liste des lettre que le joueur peut proposer */
  public ObservableList<Character> getDisponibles() {
    return disponibles;
  }

  /** Retourne la liste des lettres du mot */
  public List<StringExpression> getLettres() {
    return lettres;
  }

  /** Indique si la partie est perdue */
  public Boolean isPerdu() {
    return perdu.get();
  }

  /** Indique si les bras doivent être affichés */
  public boolean isBras() {
    return bras.get();
  }

  /** Indique si la corde doit être affichée */
  public boolean isCorde() {
    return corde.get();
  }

  /** Indique si le corps doit être affiché */
  public boolean isCorps() {
    return corps.get();
  }

  /** Indique si les jambes doivent être affichées */
  public boolean isJambes() {
    return jambes.get();
  }

  /** Indique si le mat doit être affiché */
  public boolean isMat() {
    return mat.get();
  }

  /** Indique si la potence doit être affichée */
  public boolean isPotence() {
    return potence.get();
  }

  /** Indique si le sourire doit être affiché */
  public boolean isSourire() {
    return sourire.get();
  }

  /** Indique si la barre de soutien doit être affichée */
  public boolean isSoutien() {
    return soutien.get();
  }

  /** Indique si la tête doit être affichée */
  public boolean isTete() {
    return tete.get();
  }

  /** propriété indiquant si les jambes doivent être affichées */
  public BooleanExpression jambesProperty() {
    return jambes.getReadOnlyProperty();
  }

  /** Propriété indiquant si le mat doit être affiché */
  public BooleanExpression matProperty() {
    return mat.getReadOnlyProperty();
  }

  /** Propriété indiquant si la partie est perdue */
  public BooleanExpression perduProperty() {
    return perdu;
  }

  /** Propriété indiquant si la potence doit être affichée */
  public BooleanExpression potenceProperty() {
    return potence.getReadOnlyProperty();
  }

  /** Propose une lettre.
   @param lettre la lettre à tester
   @return vrai ssi la lettre est au moins une fois dans le mot
   @throws IllegalStateException si la partie est finie
   @throws IllegalArgumentException si la lettre a déjà été proposée
   */
  public boolean propose(char lettre) {
    boolean ok = false;
    if (perdu.get()) throw new IllegalStateException("Vous êtes déjà pendus !");
    lettre = Character.toUpperCase(lettre);
    if (proposees.contains(lettre)) throw new IllegalArgumentException("Lettre déjà proposée !");
    proposees.add(lettre);
    disponibles.remove(Character.valueOf(lettre));
    char[] lettres = mot.toCharArray();
    for (int i = 0; i < lettres.length; ++i) {
      if (lettres[i] == lettre) {
        trouvees.get(i).set(true);
        ok = true;
      }
    }
    if (!ok) {
      int nbErreurs = erreurs.get();
      dessins[nbErreurs].set(true);
      erreurs.set(++nbErreurs);
    }
    return ok;
  }

  /** Propriété indiquant si le sourire doit être affiché */
  public BooleanExpression sourireProperty() {
    return sourire.getReadOnlyProperty();
  }

  /** Propriété indiquant si la barre de soutien doit être affichée */
  public BooleanExpression soutienProperty() {
    return soutien.getReadOnlyProperty();
  }

  /** Propriété indiquant si la tête doit être affichée */
  public BooleanExpression teteProperty() {
    return tete.getReadOnlyProperty();
  }

  /** Tire un nouveau mot aléatoire.
   Remet à jour les lettres proposées, les lettres disponibles et les lettres du mot.
   */
  public void tireNouveauMot() {
    String[] list = {"manger", "armoire", "examen", "travail", "constitution", "butane", "barbiturique", "vodka", "migraine", "marteau", "clou", "vis", "habituel", "ennuyeux", "catastrophique", "devoir", "maison", "frivole"};
    int de = new Random().nextInt(list.length);
    mot = list[de].toUpperCase();

    proposees.clear();
    disponibles.clear();
    for (char c = 'A'; c <= 'Z'; ++c)
      disponibles.add(c);

    prepareLettres();
    for (ReadOnlyBooleanWrapper partie : dessins)
      partie.setValue(false);
    erreurs.set(0);
  }

  /** Prépare les lettres du mot */
  private void prepareLettres() {
    trouvees.clear();
    lettres.clear();
    for (char c : mot.toCharArray()) {
      SimpleBooleanProperty trouvee = new SimpleBooleanProperty(false);
      trouvees.add(trouvee); // Lettre non encore trouvée
      SimpleStringProperty lettre = new SimpleStringProperty(Character.toString(c));
      
      /** ligne ajoutée */
      StringExpression lettreModifie = Bindings.when(trouvee).then(lettre).otherwise("_");
      
      lettres.add(lettreModifie);
    }
  }

  /** Récupère le descriptif de la partie.
   * @Throws IllegalStateException si la partie n'est pas terminée.
   */
  public Partie getPartie() {
    if (!isGagne() && !isPerdu()) throw new IllegalStateException("La partie n'est pas terminée !");
    return new Partie(mot, erreurs.get());
  }
} // public class Pendu

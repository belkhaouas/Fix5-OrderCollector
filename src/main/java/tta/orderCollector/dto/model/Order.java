package tta.orderCollector.dto.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import tta.orderCollector.dto.model.Security;

@Entity
@org.hibernate.annotations.NamedQueries({
    @org.hibernate.annotations.NamedQuery(name = "getByReference", 
      query = "from Order where reference = :reference"),
    @org.hibernate.annotations.NamedQuery(name = "getByRefOrigine", 
      query = "from Order where referenceOrigin = :referenceOrigin"),
 })
@Table(name = "ORDERS")
public class Order {
	
	@Id
	@Column(name = "REFERENCE")
	private long reference;// reference de l'ordre 
	
	@Column(name = "NUM_COMPTE")
	private String numCompte;// Numéro de compte
	
	@Column(name = "CAT_AVOIR")
	private String catAvoir;//  categorie d'avoir du compte
	
	@Column(name = "REF_ORIGIN")
	private long referenceOrigin;// reference original de l'ordre à modifier ou à annuler
	
	@Column(name = "QUANTITE")
	private int quantite; // quantite de l'ordre 
	
	@Column(name = "QUANTITE_DEV")
	private int quantiteDev; // quantite devoilée que pour les ordres Iceberg
	
	@Column(name = "QUANTITE_MIN")
	private int quantiteMin; // quantite minimal de l'ordre par defaut =0
		
	@ManyToOne
    private Security security;	
	
	@Column(name = "SENS")
	private int sens; // sens de l'ordre achat ou vente
	
	@Column(name = "TYPE_OPERATION")
	private int typeOperation; // N Nouveau M Modifié A Annulé;
	
	@Column(name = "TYPE_PRIX")
	private int typePrix; // type prix
	
	@Column(name = "PRIX")
	private double Prix; // prix de l'ordre
	
	@Column(name = "PRIX_STOP")
	private double PrixStop; // prix stop de l'ordre
	
	@Column(name = "TYPE_VALIDITE")
	private int typeValidite; //Type de la validité
	
	@Column(name = "TYPE_VALIDITE_STOP")
	private int typeValiditeStop; //temps d'arrêt déclenché en vigueur
	
	@Column(name = "DATE_VALIDITE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateValidite ;//Date validité de l'ordre  //format YYYYMMDD-HH:MM:SS.sssssssss
	
	@Column(name = "DATE_SAISIE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp dateSaisie ;//Date saisie de l'ordre  //format YYYYMMDD-HH:MM:SS.sssssssss
	
	@Column(name = "TEXT")
	private String Text; // contient la reference mère "racine" de l'ordre. elle ne change jamais pour un meme ordre.
	
//	@Column(name = "SCOPE")
//	private String scope ="1";// 0 default config 1 order not in the scope of cancel on disconnect
// 
//	@Column(name = "TYPE_PRIX_PEG")
//	private String typePrixPeg;
//	
//	@Column(name = "OFFSET_PEG")
//	private int offsetPeg;
	
	@Column(name = "STATUS")
	private int statut;
	
	public String getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	public String getCatAvoir() {
		return catAvoir;
	}
	public void setCatAvoir(String catAvoir) {
		this.catAvoir = catAvoir;
	}
	public long getReference() {
		return reference;
	}
	public void setReference(long reference) {
		this.reference = reference;
	}
	public long getReferenceOrigin() {
		return referenceOrigin;
	}
	public void setReferenceOrigin(long referenceOrigin) {
		this.referenceOrigin = referenceOrigin;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getQuantiteDev() {
		return quantiteDev;
	}
	public void setQuantiteDev(int quantiteDev) {
		this.quantiteDev = quantiteDev;
	}
	public int getQuantiteMin() {
		return quantiteMin;
	}
	public void setQuantiteMin(int quantiteMin) {
		this.quantiteMin = quantiteMin;
	}

	public int getSens() {
		return sens;
	}
	public void setSens(int sens) {
		this.sens = sens;
	}
	public int getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(int typeOperation) {
		this.typeOperation = typeOperation;
	}
	public int getTypePrix() {
		return typePrix;
	}
	public void setTypePrix(int typePrix) {
		this.typePrix = typePrix;
	}
	public double getPrix() {
		return Prix;
	}
	public void setPrix(double prix) {
		Prix = prix;
	}
	public double getPrixStop() {
		return PrixStop;
	}
	public void setPrixStop(double prixStop) {
		PrixStop = prixStop;
	}
	public int getTypeValidite() {
		return typeValidite;
	}
	public void setTypeValidite(int typeValidite) {
		this.typeValidite = typeValidite;
	}
	public int getTypeValiditeStop() {
		return typeValiditeStop;
	}
	public void setTypeValiditeStop(int typeValiditeStop) {
		this.typeValiditeStop = typeValiditeStop;
	}
	public Timestamp getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(Timestamp dateValidite) {
		this.dateValidite = dateValidite;
	}
	public Timestamp getDateSaisie() {
		return dateSaisie;
	}
	public void setDateSaisie(Timestamp dateSaisie) {
		this.dateSaisie = dateSaisie;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
	}
	
	
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [reference=").append(reference).append(", numCompte=").append(numCompte)
				.append(", catAvoir=").append(catAvoir).append(", referenceOrigin=").append(referenceOrigin)
				.append(", quantite=").append(quantite).append(", quantiteDev=").append(quantiteDev)
				.append(", quantiteMin=").append(quantiteMin).append(", security=").append(security.toString()).append(", sens=")
				.append(sens).append(", typeOperation=").append(typeOperation).append(", typePrix=").append(typePrix)
				.append(", Prix=").append(Prix).append(", PrixStop=").append(PrixStop).append(", typeValidite=")
				.append(typeValidite).append(", typeValiditeStop=").append(typeValiditeStop).append(", dateValidite=")
				.append(dateValidite).append(", dateSaisie=").append(dateSaisie).append(", Text=").append(Text)
				.append(", statut=").append(statut).append("]");
		return builder.toString();
	}
	
}

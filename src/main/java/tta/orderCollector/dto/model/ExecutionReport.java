package tta.orderCollector.dto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class ExecutionReport {

	@Id
	@Column(name = "REFERENCE")
	private long reference;// reference de l'ordre 
	
	@Column(name = "NUM_COMPTE")
	private String numCompte;// Numéro de compte

	public long getReference() {
		return reference;
	}

	public void setReference(long reference) {
		this.reference = reference;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	
	
}

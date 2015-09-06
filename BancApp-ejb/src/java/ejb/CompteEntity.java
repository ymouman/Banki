/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ELHAJMIR
 */
@Entity
public class CompteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numCompte;
    private String solde;

    @ManyToOne
    @JoinColumn(name = "ClientEntity_id")
    private ClientEntity client;
    private String type;

    public ClientEntity getClient(){
        return client;
    }
    public void setClient(ClientEntity client){
        this.client=client;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public Long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(Long numCompte) {
        this.numCompte = numCompte;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCompte != null ? numCompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteEntity)) {
            return false;
        }
        CompteEntity other = (CompteEntity) object;
        if ((this.numCompte == null && other.numCompte != null) || (this.numCompte != null && !this.numCompte.equals(other.numCompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CompteEntity[id=" + numCompte + "]";
    }

}

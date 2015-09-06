/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author younes
 */
@Entity
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String age;
    private String mail;

    @OneToMany(mappedBy = "client")
    private Collection<CompteEntity> comptes;

    public ClientEntity(){
        comptes=new ArrayList<CompteEntity>();
    }
    //l'attribut suivant "client" figure dans CompteEntity.
    public Collection<CompteEntity> getComptes(){
        return comptes;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setComptes(Collection<CompteEntity> comptes){
        this.comptes=comptes;
    }

    public void addCompte(CompteEntity compte){
        comptes.add(compte);
    }
    
    public String getAge() {
        return age;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientEntity)) {
            return false;
        }
        ClientEntity other = (ClientEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.ClientEntity[id=" + id + "]";
    }

}

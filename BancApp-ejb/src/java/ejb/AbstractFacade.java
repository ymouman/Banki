/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.PrintWriter;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 *
 * @author younes
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public void debiter(String solde,String id){
        double valeur=Double.parseDouble(solde);
        CompteEntity compte=getEntityManager().find(CompteEntity.class, Long.parseLong(id));
        compte.setSolde(String.valueOf(valeur+Double.parseDouble(compte.getSolde())));
    }

    public void crediter(String solde, String id){

        double valeur=Double.parseDouble(solde);
        CompteEntity compte=getEntityManager().find(CompteEntity.class, Long.parseLong(id));
        double existsolde=Double.parseDouble(compte.getSolde());
        compte.setSolde(String.valueOf(existsolde-valeur));
        
        
        
    }

    public void transfert(String solde, String src, String dest){
        double valeur=Double.parseDouble(solde);
        CompteEntity srccompte=getEntityManager().find(CompteEntity.class, Long.parseLong(src));
        CompteEntity destcompte=getEntityManager().find(CompteEntity.class, Long.parseLong(dest));
        double existsolde=Double.parseDouble(srccompte.getSolde());
        
        srccompte.setSolde(String.valueOf(existsolde-valeur));
        destcompte.setSolde(String.valueOf(Double.parseDouble(destcompte.getSolde())+valeur));
            
        
        
    }

    public List<T> findcompteByClient(Long idclient){
        String req="select p from CompteEntity p where p.cliententity_id=:ident ";
        Query request=(Query)getEntityManager().createQuery(req);
        request.setParameter("ident",idclient);
        return request.getResultList();
    }

    public List<T> findby(Long id,String nom,String prenom,String age,PrintWriter out){
        String req="select p from ClientEntity p ";
        String conds="";
        Query request;
        int i=0,j=0,k=0;
        if(id.compareTo(Long.parseLong("0"))!=0){
                request=(Query)getEntityManager().createQuery(req+" where p.id=:ident");
                request.setParameter("ident",id);
        }else{
                if(!nom.equals("")){
                    conds=conds+"p.nom=:nom ";
                    i=1;
                }
                if(!prenom.equals("")){
                    conds=conds+ (i==0?" ":" and ")+"p.prenom=:prenom";
                    j=1;
                }
                if( !age.equals("")){
                    conds=conds+ ((j==0 && i==0)?" ":" and ")+"p.age=:age";
                    k=1;
                }

                if(i+j+k!=0){
                    req=req+" where "+conds;
                }

                request=(Query)getEntityManager().createQuery(req);
                if(i!=0){
                    request.setParameter("nom", nom);
                }
                if(j!=0){
                    request.setParameter("prenom", prenom);
                }
                if(k!=0){
                    request.setParameter("age", age);
                }
        }

        return request.getResultList();
    }

    public List<T> find_compte_by(Long numCompte,String solde,String type,PrintWriter out){
        String req="select p from CompteEntity p ";
        String conds="";
        Query request;
        int i=0,j=0,k=0;
        if(numCompte.compareTo(Long.parseLong("0"))!=0){
                request=(Query)getEntityManager().createQuery(req+" where p.numCompte=:ident");
                request.setParameter("ident",numCompte);
        }else{
                if(!solde.equals("")){
                    conds=conds+"p.solde=:solde ";
                    i=1;
                }
                if(!type.equals("")){
                    conds=conds+ (i==0?" ":" and ")+"p.type=:type";
                    j=1;
                }

                if(i+j!=0){
                    req=req+" where "+conds;
                }

                request=(Query)getEntityManager().createQuery(req);
                if(i!=0){
                    request.setParameter("solde", solde);
                }
                if(j!=0){
                    request.setParameter("type", type);
                }
                
        }

        return request.getResultList();
    }





}

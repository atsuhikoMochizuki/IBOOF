package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class DaoMarque implements I_Crud {

    private Marque marque;

    public DaoMarque() {
    }

    public DaoMarque(Marque marque) {
        this.marque = marque;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public String lecture(String marque) {
        return (String) em.createNativeQuery("SELECT marq.nom_marque FROM Marque AS marq WHERE marq.nom_marque = ?", Marque.class)
                .setParameter(1, marque).getSingleResult();
    }

    @Transactional
    @Override
    public void ajouter(String marque) {
        em.createNativeQuery("INSERT INTO Marque (nom_marque) VALUES (?)")
                .setParameter(1, marque)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Marque as marq SET marq.nom_marque = marq.nom_marque + :increment)")
                .setParameter("increment", this.marque.getNom_marque())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Marque as marq WHERE marq.nom_marque = ?")
                .setParameter(1, this.marque
                        .getNom_marque()).executeUpdate();
    }
}

package fr.diginamic.service;

import fr.diginamic.dataAccessLayer.DaoMarque;
import fr.diginamic.dataAccessLayer.I_Crud;
import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class Services_search implements I_services_search {

    private Marque marque;
    private Categorie categorie;

    String nomMarque;
    String nomCategorie;

    public Services_search() {
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();


    @Transactional
    @Override
    public void search_BestProducts_byBrand() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "Marque AS marq, " +
                                "NutriScore AS nutri " +
                                "WHERE marq.nom_marque = ? " +
                                "AND nutri.valeurScore = \"a\"" +
                                "LIMIT 10")
                .setParameter(1, getMarque(nomMarque))
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_BestProducts_byCategory() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "Categorie AS categ, " +
                                "NutriScore AS nutri " +
                                "WHERE categ.nom_categorie = ? " +
                                "AND nutri.valeurScore = \"a\"" +
                                "LIMIT 10")
                .setParameter(1, getCategorie())
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_BestProducts_byBrandAndCategorie() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "LIMIT 10")
                .setParameter(1, getCategorie())
                .setParameter(2, getMarque(nomMarque))
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_IngredientsNbrInProduct() {
        List resultList = em.createNativeQuery(
                "SELECT ingt.nom_ingredient, COUNT(nom_produit) AS counter FROM Ingredient AS ingt " +
                        "RIGHT JOIN JOINT_TABLE_INGREDIENT_PRODUIT AS jtip ON jtip.id_ingredient = ingt.id_ingredient" +
                        "RIGHT JOIN Produit AS prod ON prod.id_produit = jtip.id_produit" +
                        "GROUP BY nom_produit" +
                        "ORDER BY counter DESC" +
                        "LIMIT 10").getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_AllergensNbrInProduct() {
        List resultList = em.createNativeQuery(
                "SELECT allerg.nom_allergene, COUNT(nom_produit) AS counter FROM Allergene AS allerg" +
                        "RIGHT JOIN JOINT_TABLE_ALLERGENE_PRODUIT AS jtip ON jtip.id_allergene = allerg.id_allergene" +
                        "RIGHT JOIN Produit AS prod ON prod.id_produit = jtip.id_produit" +
                        "GROUP BY nom_produit" +
                        "ORDER BY counter DESC" +
                        "LIMIT 10").getResultList();


        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_AdditivesNbrInProduct() {
        List resultList = em.createNativeQuery(
                "SELECT additi.nom_additif, COUNT(nom_produit) AS counter FROM Allergene AS additi" +
                        "RIGHT JOIN JOINT_TABLE_ADDITIF_PRODUIT AS jtip ON jtip.id_additif = additi.id_additif" +
                        "RIGHT JOIN Produit AS prod ON prod.id_produit = jtip.id_produit" +
                        "GROUP BY nom_produit" +
                        "ORDER BY counter DESC" +
                        "LIMIT 10").getResultList();

        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public String getMarque(String marque) {
        I_Crud crud = new DaoMarque();
        nomMarque = (String) crud.lecture(marque);
        return nomMarque;
    }

    public String getCategorie() {
        I_Crud crud = new DaoMarque();
        nomCategorie = (String) crud.lecture(categorie);
        return nomCategorie;
    }

}

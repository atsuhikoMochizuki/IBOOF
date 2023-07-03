package fr.diginamic.service.dal;

import fr.diginamic.dataAccessLayer.DaoMarque;
import fr.diginamic.dataAccessLayer.I_Crud;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaoMarqueTest {

    @Test
    public void lecture(){
        String marqueTest = "naturaline";

        I_Crud crud = new DaoMarque();
        assertEquals("naturaline", crud.lecture(marqueTest));
    }

    @Test
    public void ajouter() {
    }

    @Test
    public void modifier() {
    }

    @Test
    public void supprimer() {
    }
}
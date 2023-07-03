package fr.diginamic.dataAccessLayer;

import jakarta.transaction.Transactional;

public interface I_Crud {

    @Transactional
    Object lecture(String word);
    @Transactional
    void ajouter(String word);

    @Transactional
    void modifier();

    @Transactional
    void supprimer();
}

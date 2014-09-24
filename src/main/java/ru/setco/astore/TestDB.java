package ru.setco.astore;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ru.setco.astore.entity.Category;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author OLEG
 */
public class TestDB {
    
    public static void main(String[] args) {
        System.out.println("TestDB");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("localPU");
        System.out.println("emf: " + emf);
        
        EntityManager em = emf.createEntityManager();
        System.out.println("em: " + em);
        
        List<Category> categories = em.createNamedQuery("Category.findAll").getResultList();
        System.out.println("Categories: " + categories);

    }
            
    
}

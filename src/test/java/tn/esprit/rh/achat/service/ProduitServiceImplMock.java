package tn.esprit.rh.achat.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.*;


@ExtendWith(MockitoExtension.class)
 class ProduitServiceImplMock {

    ProduitRepository produitRepository = Mockito.mock(ProduitRepository.class);

    @InjectMocks
    ProduitServiceImpl produitService;

    Produit produit1 = new Produit(1L, "f1",
            "f2", 111, new Date(), new Date(), new Stock(),
            new HashSet<>(), new CategorieProduit());

    List<Produit> produits = new ArrayList<Produit>() {
        {
            add(produit1);

            add(new Produit(2L, "f3", "f4",222 , new Date(), new Date(), new Stock(),
                    new HashSet<>(), new CategorieProduit()));
            add(new Produit(3L, "f5", "f6",333 , new Date(), new Date(), new Stock(),
                    new HashSet<>(), new CategorieProduit()));
        }
    };

    @Test
     void testretrieveAllProduits()
    {
        Mockito.when(produitRepository.findAll()).thenReturn(produits);
        List<Produit>produitList=produitService.retrieveAllProduits();
        Assertions.assertEquals(3,produitList.size());
    }

    @Test
     void testretrieveProduit()
    {
        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit1));
        Produit produit=produitService.retrieveProduit(1L);
        Assertions.assertEquals("f1",produit1.getCodeProduit());
    }

    @Test
     void testaddProduit()
    {
        Mockito.when(produitRepository.save(produits.get(1))).thenReturn(produits.get(1));
       Produit savedProduct=produitService.addProduit(produits.get(1));
        Assertions.assertEquals(111,produit1.getPrix());
    }

    @Test
     void testupdateProduit()
    {
        Produit updatedProduit = new Produit(1L, "UpdatedCode", "Updatedlibelle", 999, new Date(), new Date(), new Stock(), new HashSet<>(), new CategorieProduit());
        Mockito.when(produitRepository.save(updatedProduit)).thenReturn(updatedProduit);
        Produit savedProduct = produitService.updateProduit(updatedProduit);
        Mockito.verify(produitRepository, Mockito.times(1)).save(updatedProduit);
        Assertions.assertEquals("UpdatedCode", savedProduct.getCodeProduit());
        Assertions.assertEquals("Updatedlibelle", savedProduct.getLibelleProduit());
        Assertions.assertEquals(999, savedProduct.getPrix());

    }
    @Test
     void testdeleteProduit()
    {
        Mockito.doNothing().when(produitRepository).deleteById(Mockito.anyLong());
        produitService.deleteProduit(3L);
        Mockito.verify(produitRepository, Mockito.times(1)).deleteById(3L);
    }
}

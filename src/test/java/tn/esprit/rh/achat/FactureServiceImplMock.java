package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.entities.Facture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class FactureServiceImplMock {

    @Mock
    FactureRepository factureRepository;

    @InjectMocks
    FactureServiceImpl factureService;

    Facture facture = new Facture(100.0f, 500.0f, new Date(), new Date(), false);
    Facture facture2 = new Facture(100.0f, 500.0f, new Date(), new Date(), false);
    Facture facture3 = new Facture(100.0f, 500.0f, new Date(), new Date(), false);
    Facture facture4 = new Facture(100.0f, 500.0f, new Date(), new Date(), false);
    List<Facture> factures = new ArrayList<Facture>() {
        {
            add(facture);
            add(facture2);
            add(facture3);
            add(facture4);
        }
    };

    @Test
    void TestretrieveAllFactures() {
        Mockito.when(factureRepository.findAll()).thenReturn(factures);
        List<Facture> produitList = factureService.retrieveAllFactures();
        Assertions.assertEquals(4, produitList.size());
    }

    @Test
    public void TestRetrieveFacture() {

        Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(facture))
        ;
        Facture facture5 = factureService.retrieveFacture(2L);
        Assertions.assertNotNull(facture5);
    }

    @Test
    void TestaddProduit()
    {
        Mockito.when(factureRepository.save(factures.get(1))).thenReturn(factures.get(1));
        Facture fac=factureService.addFacture(factures.get(1));
        Assertions.assertEquals(500.0f,facture.getMontantFacture());
    }

}

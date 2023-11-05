package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReglementServiceImplMock {

    @Mock
    ReglementRepository reglementRepository;

    @InjectMocks
    ReglementServiceImpl reglementService;

    Reglement reglement1 = new Reglement(1L, 100f, 0f, true, new Date(), new Facture());

    List<Reglement> reglements = Arrays.asList(
            reglement1,
            new Reglement(2L, 200f, 0f, true, new Date(), new Facture()),
            new Reglement(3L, 300f, 0f, true, new Date(), new Facture())
    );

    @Test
    void testRetrieveAllReglements() {
        Mockito.when(reglementRepository.findAll()).thenReturn(reglements);
        List<Reglement> result = reglementService.retrieveAllReglements();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testAddReglement() {
        Mockito.when(reglementRepository.save(reglement1)).thenReturn(reglement1);
        Reglement savedReglement = reglementService.addReglement(reglement1);
        Assertions.assertEquals(100f, savedReglement.getMontantPaye());
    }

    @Test
    void testRetrieveReglement() {
        Mockito.when(reglementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reglement1));
        Reglement result = reglementService.retrieveReglement(1L);
        Assertions.assertEquals(100f, result.getMontantPaye());
    }

    @Test
    void testRetrieveReglementByFacture() {
        Mockito.when(reglementRepository.retrieveReglementByFacture(Mockito.anyLong())).thenReturn(reglements);
        List<Reglement> result = reglementService.retrieveReglementByFacture(1L);
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        Mockito.when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(600f);
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        Assertions.assertEquals(600f, result);
    }
}

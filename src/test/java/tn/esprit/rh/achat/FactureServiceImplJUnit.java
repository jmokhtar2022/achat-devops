package tn.esprit.rh.achat;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.services.IFactureService;
import tn.esprit.rh.achat.entities.Facture;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FactureServiceImplJUnit {

    @Autowired
    IFactureService factureService;

    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Facture> listUsers = factureService.retrieveAllFactures();
        Assertions.assertEquals(5, listUsers.size());
    }

    @Test
    @Order(2)
    public void AddFacture() {
            Facture facture = new Facture(100.0f, 500.0f, new Date(), new Date(), false);
            factureService.addFacture(facture);
            assertThat(facture.getIdFacture()).isGreaterThan(0);
    }
    @Test
    @Order(3)
    public void getFacture() {
        Facture facture = factureService.retrieveFacture(1L);

        assertThat(facture.getIdFacture()).isEqualTo(1L);
    }


}

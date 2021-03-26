package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @Test
    public void alussaOikeaMaaraRahaa() {
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void alussaNollaMyytyaLounasta() {
        assertTrue(paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty()==0);
    }
    
    @Test
    public void toimiikoRiittavanSuuriEdullinenKateisosto() {
        int vaihto = paate.syoEdullisesti(300);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1 && paate.kassassaRahaa() == 100240 && vaihto == 60);
    }
    
    @Test
    public void toimiikoRiittavanSuuriMaukasKateisosto() {
        int vaihto = paate.syoMaukkaasti(420);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1 && paate.kassassaRahaa() == 100400 && vaihto == 20);
    }
    
    @Test
    public void toimiikoRiittamatonEdullinenKateisosto() {
        int vaihto = paate.syoEdullisesti(200);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0 && paate.kassassaRahaa() == 100000 && vaihto == 200);
    }
    
    @Test
    public void toimiikoRiittamatoniMaukasKateisosto() {
        int vaihto = paate.syoMaukkaasti(29);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0 && paate.kassassaRahaa() == 100000 && vaihto == 29);
    }
    
    @Test
    public void toimiikoRiittavanSuuriEdullinenKorttiosto() {
        Maksukortti kortti = new Maksukortti(500);
        boolean onnistuiko = paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 1 && paate.kassassaRahaa() == 100000 && onnistuiko && kortti.saldo() == 260);
    }
    
    @Test
    public void toimiikoRiittavanSuuriMaukasKorttiosto() {
        Maksukortti kortti = new Maksukortti(500);
        boolean onnistuiko = paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 1 && paate.kassassaRahaa() == 100000 && onnistuiko && kortti.saldo() == 100);
    }
    
    @Test
    public void toimiikoLiianPieniEdullinenKorttiosto() {
        Maksukortti kortti = new Maksukortti(100);
        boolean onnistuiko = paate.syoEdullisesti(kortti);
        assertTrue(paate.edullisiaLounaitaMyyty() == 0 && !onnistuiko && kortti.saldo() == 100);
    }
    
    @Test
    public void toimiikoLiianPieniMaukasKorttiosto() {
        Maksukortti kortti = new Maksukortti(100);
        boolean onnistuiko = paate.syoMaukkaasti(kortti);
        assertTrue(paate.maukkaitaLounaitaMyyty() == 0 && !onnistuiko && kortti.saldo() == 100);
    }
    
    @Test
    public void toimiikoKortilleLataus() {
        Maksukortti kortti = new Maksukortti(100);
        paate.lataaRahaaKortille(kortti, 124);
        assertTrue(paate.kassassaRahaa() == 100124 && kortti.saldo() == 224);
    }
    
    @Test
    public void negatiivisenSummanLatausKortilleEiTeeMitaan() {
        Maksukortti kortti = new Maksukortti(100);
        paate.lataaRahaaKortille(kortti, -999);
        assertTrue(paate.kassassaRahaa() == 100000 && kortti.saldo() == 100);
    }
}

package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void onkoSaldoOikein() {
        assertTrue(kortti.saldo()==10);      
    }
    
    @Test
    public void toimiikoSaldonKasvatus() {
        kortti.lataaRahaa(2);
        assertTrue(kortti.saldo()==12);      
    }
    
    @Test
    public void vaheneekoSaldoOikein() {
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo()==5);      
    }
    
    @Test
    public void muuttuukoSaldoJosRahaEiRiita() {
        kortti.otaRahaa(12);
        assertTrue(kortti.saldo()==10);      
    }
    
    @Test
    public void palauttaakoTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(2)==true);      
    }
    
    @Test
    public void palauttaakoFalseJosRahatEiRiita() {
        assertTrue(kortti.otaRahaa(50)==false);      
    }
    
    @Test
    public void toimiikoToSTring() {
        assertEquals("saldo: 0.10", kortti.toString());    
    }
}

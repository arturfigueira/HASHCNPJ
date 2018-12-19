package com.ca.datamasker.custom.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CNPJValidatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkValidCNPJ() {
        final String validCNPJ = "00500627000101";
        //final String validCNPJ = "85186183000134";
        assertTrue(validCNPJ+" is considered a valid CNPJ", CNPJValidator.isValid(validCNPJ));
    }

    @Test
    public void checkInValidCNPJ() {
        final String invalidCNPJ = "44158321000199";
        assertFalse(invalidCNPJ+" is considered a invalid CNPJ", CNPJValidator.isValid(invalidCNPJ));
    }

    @Test
    public void checkSequenceCNPJ() {
        final String invalidCNPJ = "11111111111111";
        assertFalse(invalidCNPJ+" is considered a invalid CNPJ", CNPJValidator.isValid(invalidCNPJ));
    }

    @Test
    public void checkInvalidLengthCNPJ() {
        final String invalidCNPJ = "1111111111111111";
        assertFalse(invalidCNPJ+" is considered a invalid CNPJ", CNPJValidator.isValid(invalidCNPJ));
    }
}
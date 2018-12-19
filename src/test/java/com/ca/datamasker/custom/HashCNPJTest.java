package com.ca.datamasker.custom;

import com.ca.datamasker.custom.utils.CNPJValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashCNPJTest {

    private static final String validCNPJ = "67.736.139/0001-79";
    private static final String validCleanCNPJ = "00500627000101";
    private static final String invalidCNPJ = "00500627000199";

    HashCNPJ hashCNPJ;

    @Before
    public void setUp() throws Exception {
        hashCNPJ = new HashCNPJ();
    }

    @Test
    public void maskValidCleanCNPJ() {
        final String hashedValue = hashCNPJ.hashIt(validCleanCNPJ);
        assertTrue(CNPJValidator.isValid(hashedValue));
    }

    @Test
    public void maskUncleanedValidCNPJ(){
        final String hashedValue = hashCNPJ.hashIt(validCNPJ);
        assertTrue(CNPJValidator.isValid(hashedValue.replaceAll("\\D", "")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void maskInValidCNPJ() {
        hashCNPJ.hashIt(invalidCNPJ);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maskInvalidLengthCNPJ() {
        hashCNPJ.hashIt("00500627");
    }

    @Test(expected = IllegalArgumentException.class)
    public void maskLargLengthCNPJ() {
        hashCNPJ.hashIt("00500627000199123");
    }

    @Test
    public void checkMaskIntegrityForCleanCNPJ(){
        final String firstHash = hashCNPJ.hashIt(validCleanCNPJ);
        final String secondHash = hashCNPJ.hashIt(validCleanCNPJ);
        assertEquals("same input value, should return always the same hashed value", firstHash, secondHash);
    }

    @Test
    public void checkMaskIntegrityForUncleanCNPJ(){
        final String firstHash = hashCNPJ.hashIt(validCNPJ);
        final String secondHash = hashCNPJ.hashIt(validCNPJ);
        assertEquals("same input value, should return always the same hashed value", firstHash, secondHash);
    }
}
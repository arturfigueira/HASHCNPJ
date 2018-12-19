package com.ca.datamasker.custom.utils;

/**
 * Class with sole purpose to expose methods to validate a CNPJ
 * More about Brazilian CNPJ here {@link https://en.wikipedia.org/wiki/CNPJ}
 */
public class CNPJValidator {

    private static final int CLEAN_CNPJ_WITH_CHECK_DIGIT_LENGTH = 14;

    private CNPJValidator(){}

    /**
     * Validate if a given @cnpj is valid. CNPJ should have its Check Digit
     * and should contains only numbers.
     * @param cnpj
     * @return True if its valid, false otherwise
     */
    public static boolean isValid(final String cnpj){
        boolean isValidCNPJ = false;
        if(cnpj.length() == CLEAN_CNPJ_WITH_CHECK_DIGIT_LENGTH && !hasSequences(cnpj)){
            final String cnpjWithouDV = cnpj.substring(0, cnpj.length() - 2);
            final String inputDV = cnpj.substring(cnpj.length()-2);
            final String calculatedDV = CNPJCheckDigitCalculator.calculate(cnpjWithouDV);
            isValidCNPJ = inputDV.equals(calculatedDV);
        }
        return isValidCNPJ;
    }

    /**
     * Check if the given CNPJ, without check digit, has a sequence of repeated numbers,
     * ex: 11111111111 should be considered invalid
     * @param CNPJ
     * @return True if it has sequences or false otherwise
     */
    static boolean hasSequences(final String CNPJ){
        return CNPJ.matches("^(\\d)\\1{13}$");
    }
}

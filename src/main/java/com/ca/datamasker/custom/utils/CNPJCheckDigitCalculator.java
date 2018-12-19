package com.ca.datamasker.custom.utils;

public class CNPJCheckDigitCalculator {

    private static final int START_WEIGHT = 2;

    private CNPJCheckDigitCalculator(){}

    /**
     * Calculate both check digits values of a given VALID {@param cnpj}. This cnpj
     * should contains only numbers
     * @param cnpj
     * @return both Check Digits values of a cnpj
     */
    public static String calculate(final String cnpj){
        final char firstCDPart = firstDigitCalculator(cnpj);
        final char secondCDPart = secondDigitCalculator(cnpj, firstCDPart);
        return new String(""+firstCDPart+secondCDPart);
    }

    private static int mod11(final int value){
        int mod11 = value % 11;
        return (mod11 == 0 || mod11 == 1) ? 0 :(11-mod11);
    }

    static char checkDigitCalculator(final String cnpjCandidate){
        int columnSum = 0;
        int columnWeight = START_WEIGHT;
        int lastPositionWithoutDV = cnpjCandidate.length()-1;

        for (int i=lastPositionWithoutDV; i>=0; i--) {
            final int columnNumericValue = Character.getNumericValue(cnpjCandidate.charAt(i));
            columnSum += (columnNumericValue * columnWeight);
            columnWeight++;
            columnWeight = (columnWeight==10) ? START_WEIGHT : columnWeight;
        }
        return Character.forDigit(mod11(columnSum), 10);
    }

    static char firstDigitCalculator(final String cnpj){
        return checkDigitCalculator(cnpj);
    }

    static char secondDigitCalculator(String cnpj, final char firstDV){
        return checkDigitCalculator(cnpj+firstDV);
    }

}

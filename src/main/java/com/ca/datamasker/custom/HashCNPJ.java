package com.ca.datamasker.custom;

import com.ca.datamasker.custom.utils.CNPJCheckDigitCalculator;
import com.ca.datamasker.custom.utils.CNPJValidator;
import com.grid_tools.products.datamasker.Datamasker;
import com.grid_tools.products.datamasker.IMaskFunction;
import com.grid_tools.products.datamasker.randfunctions;

public class HashCNPJ implements IMaskFunction {

    @Override
    public Object mask(Object... objects) {
        String maskedValue = null;

        final String inputValue = (String) objects[0];
        if(inputValue == null || inputValue.trim().isEmpty()){
            maskedValue = inputValue;
        }

        try{
            maskedValue = hashIt(inputValue);
        }catch (IllegalArgumentException e){
            Datamasker.processOutputs(Datamasker.formatMessage("m0345-hasherr", new String[] { "HASH" }));
            Datamasker.processErrors(Datamasker.formatMessage("m0345-hasherr", new String[] { "HASH" }));
            Datamasker.processOutputs(Datamasker.formatMessage("m0155-DBValue", new String[] { inputValue }));
            System.exit(1);
        }
        return maskedValue;
    }

    protected String hashIt(final String originalCNPJ){
        final String cnpjWithoutDV = originalCNPJ.substring(0, originalCNPJ.length() - 2);

        if(!CNPJValidator.isValid(cleanUp(originalCNPJ))){
            throw new IllegalArgumentException("This CNPJ is invalid and cant be hashed");
        }

        final String hashedCnpj = randfunctions.formatHash(cnpjWithoutDV);
        final String cleanedHashedCNPJ = cleanUp(hashedCnpj);
        return hashedCnpj+ CNPJCheckDigitCalculator.calculate(cleanedHashedCNPJ);

 /*       final String cleanedCNPJ = originalCNPJ.replaceAll("\\D", "");

        if(!CNPJValidator.isValid(cleanedCNPJ)){
            throw new IllegalArgumentException("This CNPJ is invalid and cant be hashed");
        }

        final String cnpjWithoutDV = cleanedCNPJ.substring(0, cleanedCNPJ.length() - 2);
        final String hashedCnpj = randfunctions.formatHash(cnpjWithoutDV);
        return hashedCnpj+ CNPJCheckDigitCalculator.calculate(hashedCnpj);*/
    }

    private String cleanUp(final String cnpj){
        return cnpj.replaceAll("\\D", "");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;

import java.util.ArrayList;

/**
 *
 * @author fc.corporation
 */
public class ParamProcessor {
   private FieldProcessor  parametersFormal;
    
    public ParamProcessor(){
        this.parametersFormal = new FieldProcessor();
    }
    public void setParametersFormal(String linha){
        final char BEGINPARAM = '(';
        final String ENDPARAM = ")";
        String[] listString;
        linha = linha.substring(linha.indexOf(BEGINPARAM)+1).replace(ENDPARAM, "");
        listString = linha.split(",");
        for(String str: listString){
            this.parametersFormal.setListVariaveis(str);
        }
    }
    public void isParamFormal(String linha){
        
    }
    public String toString(){
        return this.parametersFormal.toString();
    }   
}
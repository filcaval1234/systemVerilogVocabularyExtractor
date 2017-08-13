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
abstract class Modulo {
    protected boolean beginStruct;
    protected boolean endStruct;
    protected String BEGINSTRUCT;
    protected String ENDSTRUCT;
    
    public Modulo(String begin, String end){
        this.BEGINSTRUCT = begin;
        this.ENDSTRUCT = end;
        this.beginStruct = false;
        this.endStruct = false;
    }
    abstract void setFields(String originalLinha);
    
    abstract void setVariableAndCommentlocal(String linha);
    
    protected boolean isModule(String linha){
        boolean state = false;
        if(linha.startsWith(BEGINSTRUCT)){
            state = true;
            beginStruct = true;
            endStruct = false;
        }
        else if(linha.startsWith(ENDSTRUCT)){
            beginStruct = false;
            endStruct = true;
        }
        return state;
    }
    protected String filterAccessMode(String linha){
        if(linha.contains(BEGINSTRUCT+" ") && !linha.contains(ENDSTRUCT)&& !linha.contains("=")){
            linha = linha.substring(linha.indexOf(BEGINSTRUCT));
        }
        return linha;
    }
    protected String filterIndentation(String linha){
        int i=0;
        for(;i < linha.length(); i++){
            char teste = linha.charAt(i);
            if(linha.charAt(i) == ' ' || linha.charAt(i) == 9) {
                continue;
            }else break;
        }
        return linha.substring(i);
    }
}
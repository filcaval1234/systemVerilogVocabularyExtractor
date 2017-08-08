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
    
    protected boolean isStruct(String linha){
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
        if(linha.contains(BEGINSTRUCT) && !linha.contains(ENDSTRUCT)&& !linha.contains("=")){
            linha = linha.substring(linha.indexOf(BEGINSTRUCT));
        }
        return linha;
    }
    protected String filterIndentation(String linha){
        String identation = "  ";
        return linha.replace(identation, "");
    }
    
}

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
public class TaskProcessor extends Estruct{
    ArrayList<TaskData> tkda;
    String delay;
    
    public TaskProcessor() {
        super("task", "endtask");
        tkda = new ArrayList<TaskData>();
    }
    @Override
    void setFields(String originalLinha) {
        final String SPACE = " ";
        final String PARENTESES = "(";
        String[] listWord;
        TaskData taskdata = null;
        String linha = this.filterIndentation(originalLinha);
        linha = this.filterAccessMode(linha);
        if(isStruct(linha)){
            linha = linha.substring(0, linha.indexOf(PARENTESES));
            listWord = linha.split(SPACE);
            if(listWord.length == 2){
                taskdata = new TaskData(listWord[listWord.length-1]);
                taskdata.setParam(originalLinha);
                this.tkda.add(taskdata);
            }
        }
        this.setVariableAndCommentlocal(originalLinha);
    }
        
    }
    @Override
    void setVariableAndCommentlocal(String linha) {
        if(beginStruct && !endStruct){
            this.tkda.get(tkda.size()-1).setLocalField(linha);
            this.tkda.get(tkda.size()-1).setCommentLocal(linha);
        }
        else if(endStruct){
            beginStruct = false;
            endStruct = false;
        }
        
    }
    
}

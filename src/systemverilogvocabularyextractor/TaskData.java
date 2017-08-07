/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;

/**
 *
 * @author fc.corporation
 */
public class TaskData extends MethodData{
    private String dalay;
    public TaskData(String name) {
        super(name);
    }
    public void setDelay(String delay){
        this.dalay = delay;
    }
    @Override
    public String toString(){
        String metodo ="nome da task: "+this.name+"\n";
        metodo += "----------------parametros----------------------------+\n";
        metodo += this.param;
        metodo += "--------------variaveis Locais----------------------\n";
        metodo += this.localField+"\n";
        metodo += "--------------Comentarios----------------------\n";
        metodo += this.commentsFunction;
        metodo += "-----------------------------------------------------\n";
        return metodo;
    }   
}
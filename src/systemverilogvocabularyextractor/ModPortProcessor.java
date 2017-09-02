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
public class ModPortProcessor extends Modulo{
    private ArrayList<ModPortData> arrayModPorts;
    private int size;
    private FieldProcessor fieldProcessorModPort;
    private static final String BEGIMMODPORT = "modport";
    private static final String ENDMODPORT = ");";

    public ModPortProcessor() {
        super(ModPortProcessor.BEGIMMODPORT, ModPortProcessor.ENDMODPORT);
        this.arrayModPorts = new ArrayList<ModPortData>();
        this.fieldProcessorModPort = new FieldProcessor();
    }
    /**
     * 
     * @param sourceLine 
     */
    @Override
    void setFields(String sourceLine) {
        sourceLine = this.filterIndentation(sourceLine);
        ModPortData TempModPort;
        if(this.isModule(sourceLine)){
            String name = sourceLine.substring(sourceLine.indexOf(" "), 
                    sourceLine.indexOf("("));
            TempModPort = new ModPortData(name);
        }
        this.setVariableAndCommentlocal(sourceLine);
    }
    @Override
    void setVariableAndCommentlocal(String linha) {
        if(this.beginStruct){
            this.getDirectionsFields(linha);
        }
        else if(this.endStruct){
            this.arrayModPorts.get(size).setFieldsModPort(this.fieldProcessorModPort);
            this.fieldProcessorModPort = new FieldProcessor();
            this.beginStruct = false;
        }
    }
    
    public void getDirectionsFields(String sourceLine){
        this.fieldProcessorModPort.setListVariaveis(sourceLine);
    }
    
    public String toString(){
        String modPortProcessor = "";
        for(ModPortData modPort: this.arrayModPorts){
            modPortProcessor += modPort;
        }
        return modPortProcessor;
    }
}
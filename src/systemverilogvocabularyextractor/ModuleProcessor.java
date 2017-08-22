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
public class ModuleProcessor extends Modulo{
    private ArrayList<ModuleData> arrayModuleData;
    private int size;
    private static final String BEGINMODULE = "module";
    private static final String ENDMODULE = "endmodule";

    public ModuleProcessor() {
        super(ModuleProcessor.BEGINMODULE, ModuleProcessor.ENDMODULE);
        this.arrayModuleData = new ArrayList<ModuleData>();
    }

    @Override
    void setFields(String sourceLine) {
        sourceLine = this.filtration(sourceLine, true);
        String nameModule = sourceLine.substring(sourceLine.indexOf(" "));
        ModuleData tempModuleData = new ModuleData(nameModule);
        this.arrayModuleData.add(tempModuleData);
        this.size+=1;
    }

    @Override
    void setVariableAndCommentlocal(String sourceLine) {
        if(this.beginStruct && !this.endStruct){
            ModuleData referenceModule = this.arrayModuleData.get(size);
            referenceModule.setMdpr(sourceLine);
            if(!referenceModule.getMdpr().isModule())
                referenceModule.setFdpr(sourceLine);
            
        }
        else if(this.endStruct){
            this.beginStruct = false;
            this.endStruct = false;
        }
    }
    
}
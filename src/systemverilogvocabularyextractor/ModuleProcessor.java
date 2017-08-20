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
    ArrayList<ModuleData> medt;
    private static final String BEGINMODULE = "module";
    private static final String ENDMODULE = "endmodule";

    public ModuleProcessor() {
        super(ModuleProcessor.BEGINMODULE, ModuleProcessor.ENDMODULE);
    }

    @Override
    void setFields(String originalLinha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void setVariableAndCommentlocal(String linha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}
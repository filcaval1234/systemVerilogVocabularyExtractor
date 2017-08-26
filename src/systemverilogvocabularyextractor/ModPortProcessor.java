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
    private static final String BEGIMMODPORT = "modport";
    private static final String ENDMODPORT = ");";

    public ModPortProcessor(String begin, String end) {
        super(ModPortProcessor.BEGIMMODPORT, ModPortProcessor.ENDMODPORT);
        this.arrayModPorts = new ArrayList<ModPortData>();
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
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
public class InterfaceProcessor extends Modulo{
    private ArrayList<InterfaceData> arrayInterface;
    private CommentProcessor genericCommentProcessorInterface;
    private static final String BEGIMINTERFACE = "interface";
    private static final String ENDINTERFACE = "endinterface";
    private int size;
    
    public InterfaceProcessor(){
        super(InterfaceProcessor.BEGIMINTERFACE, InterfaceProcessor.ENDINTERFACE);
        this.arrayInterface = new ArrayList<InterfaceData>();
        this.genericCommentProcessorInterface = new CommentProcessor();
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
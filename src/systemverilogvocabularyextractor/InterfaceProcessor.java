/**
 * A classe InterfaceProcessor encapsula os campos e os método necessarios para
 * a modelagem de um processador de interface extendendo da classe abstrata Modulo.
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
    
    /**
     * O construtor da classe que não recebe nemhum argumento e instacia 
     * seus objetos.
     */
    public InterfaceProcessor(){
        super(InterfaceProcessor.BEGIMINTERFACE, InterfaceProcessor.ENDINTERFACE);
        this.arrayInterface = new ArrayList<InterfaceData>();
        this.genericCommentProcessorInterface = new CommentProcessor();
    }   
    /**
     * O método
     * @param originalLinha 
     */
    @Override
    void setFields(String originalLinha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     * @param linha 
     */
    @Override
    void setVariableAndCommentlocal(String linha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
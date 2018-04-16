/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemverilogvocabularyextractor;
import java.util.ArrayList;
import handleEventesVocabularyExtractor.HandleEventsComments;
/**
 *
 * @author fc.corporation
 */
public class ModuleProcessor extends AbstractModuleLanguage{
    private ArrayList<ModuleData> arrayModuleData;
    private CommentProcessor commentsFunction;
    private int size;
    private static final String BEGINMODULE = "module";
    private static final String ENDMODULE = "endmodule";
    private HandleEventsComments handleEventsComment;
    
    public ModuleProcessor(){
        super(ModuleProcessor.BEGINMODULE, ModuleProcessor.BEGINMODULE);
        this.arrayModuleData = new ArrayList<>();
        this.commentsFunction = new CommentProcessor();
    }

    public ModuleProcessor(HandleEventsComments handleEventsComments) {
        super(ModuleProcessor.BEGINMODULE, ModuleProcessor.ENDMODULE);
        this.handleEventsComment = handleEventsComments;
        this.arrayModuleData = new ArrayList<>();
        this.commentsFunction = new CommentProcessor();
    }
    public void setModuleProperties(String sourceLine){
        if(this.isModule(sourceLine)){
            this.setFields(sourceLine);
            //this.handleEventsComment.setGenericComments(this);
            this.arrayModuleData.get(size-1).setParamProcessorModuleData(sourceLine);
        }
        else{
            this.setVariableAndCommentlocal(sourceLine);
        }
    }
    @Override
    void setFields(String sourceLine) {
        try{
            sourceLine = this.filtration(sourceLine, true);
        }catch(StringIndexOutOfBoundsException stie){
            sourceLine = sourceLine.replace(";", "");
        }
        String nameModule = this.getNameAbstractModuleLanguage(sourceLine);
        ModuleData tempModuleData = new ModuleData(nameModule);
        this.arrayModuleData.add(tempModuleData);
        this.size+=1;
    }
    @Override
    void setVariableAndCommentlocal(String sourceLine) {
        if(this.beginStruct && !this.endStruct){
            ModuleData referenceModule = this.arrayModuleData.get(this.size-1);
            if(this.commentsFunction.isCommentBlock(sourceLine))
                this.commentsFunction.setComments(sourceLine);
            else{
                referenceModule.setMethodProcessorModuleData(sourceLine);
                if(!referenceModule.getMethodProcessorModuleData().isModule())
                    referenceModule.setFieldProcessorModuleData(sourceLine);
                if(referenceModule.getMethodProcessorModuleData().isModule(sourceLine)){
                    this.commentsFunction.setBeginComments(false);
                    this.commentsFunction.setEndComments(false);                    
                    this.commentsFunction = new CommentProcessor();
                }
            }
        }
    }
    public void setModuleComments(CommentProcessor comments){
        this.arrayModuleData.get(size-1).setCommentProcessorModuleData(comments);
    }
    @Override
    public String toString(){
        String modules = "";
        for(ModuleData mdl: arrayModuleData){
            modules += mdl;
        }
        return modules;
    }
    public String toXML() {
        String toXML = "";
        for(ModuleData m: this.arrayModuleData){
            toXML += m.toXML();
        }
        return toXML;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handleEventesVocabularyExtractor;
import systemverilogvocabularyextractor.*;

/**
 *
 * @author ifcardio
 */
public class HandleEventsComments {
    CommentProcessor genericComments;
    
    public HandleEventsComments(CommentProcessor referenceComment){
        this.genericComments = referenceComment;
    }
    public void setGenericComments(AbstractModuleLanguage genericProcessor){
        genericProcessor.setGenericComment(genericComments);
        this.genericComments.clearComments();
    }
    public String toString(){
        return this.genericComments.toString();
    }
}
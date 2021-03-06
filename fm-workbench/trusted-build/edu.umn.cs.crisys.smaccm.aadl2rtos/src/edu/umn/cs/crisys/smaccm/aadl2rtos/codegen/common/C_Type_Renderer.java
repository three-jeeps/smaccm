/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.stringtemplate.v4.AttributeRenderer;
import org.stringtemplate.v4.AutoIndentWriter;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public class C_Type_Renderer implements AttributeRenderer {

  /* (non-Javadoc)
   * @see org.stringtemplate.v4.AttributeRenderer#toString(java.lang.Object, java.lang.String, java.util.Locale)
   */
    
  @Override
  public String toString(Object arg0, String arg1, Locale arg2) {
    //System.out.println("Indent level is: ||" + createIndent() + "||");
    return ((Type) arg0).getCType().typeString();
  }

}

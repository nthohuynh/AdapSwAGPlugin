
package rlecompression;	
	
			
			
import rlecompressionService.*;

	

import org.apache.felix.ipojo.annotations.*;	
@Component(name="RLECompression")
/*
 * Begin the class
 *
 */
public class RLECompression 
	implements RLECompressionService
{
	@Property(name="rleCompressionProperty")
	String rleCompressionProperty;


}
	 
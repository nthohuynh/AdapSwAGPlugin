
package compression;	
import lzcompressionService.*;	
import rlecompressionService.*;	
					
	

	

import org.apache.felix.ipojo.annotations.*;	
@Component(name="Compression")
/*
 * Begin the class
 *
 */
public class Compression 
{
	@Property(name="compressionProperty")
	String compressionProperty;
	@Requires
	LZCompressionService service_fromSender;
	@Requires
	RLECompressionService service_fromSender;


}
	 
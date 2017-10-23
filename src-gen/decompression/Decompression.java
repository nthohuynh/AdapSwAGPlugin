
package decompression;	
import rledecompressionService.*;	
import lzdecompressionService.*;	
					
	

	

import org.apache.felix.ipojo.annotations.*;	
@Component(name="Decompression")
/*
 * Begin the class
 *
 */
public class Decompression 
{
	@Requires
	RLEDecompressionService service_fromReceiver;
	@Requires
	LZDecompressionService service_fromReceiver;


}
	 
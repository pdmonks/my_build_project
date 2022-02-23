package debugOutput;

/**
 * DebugOptions allows messages to be output during develop to aid in
 * debugging.
 * By amending the value in 'debugShowOutput' to 0, the messages
 * are not displayed; eg when the final application is delivered.
 * 
 * @author Peter Monks
 * @version 1.0
 */

public class DebugOptions {

	/**
	 * flag to determine whether or not debug messages are generated
	 * @return int 1 to show outputs, 0 to hide outputs
	 */
	public static int debugShowOutput() {
		return 1;
	}
	
	/**
	 * show debug outputs in console if above is 1
	 * @param debugObject object to be output as a String
	 */
	public static void debugOutput(Object debugObject) {
		if(DebugOptions.debugShowOutput() == 1) {
			System.out.println(debugObject.toString());
		}
	}
	
}

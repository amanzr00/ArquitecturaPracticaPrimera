
public class Chronometer {
	
	// Atributos
	private long initialTime;
	private long finalTime;
	private long timeChorometred;
	private int status;
	
	// constantes para representar los estados del crono
	private static final int ACTIVE = 0;
	private static final int STOPPED = 1;
	
	// Metodos
	
	/**
	 * Constructor
	 */
	public Chronometer(){
		
		status = STOPPED;
		initialTime = 0;
		finalTime = 0;
		
	}
	
	/**
	 * 
	 */
	public void activate(){
		if (status != ACTIVE){
			status = ACTIVE;
			initialTime = java.lang.System.currentTimeMillis();
		}
	}
	
	/**
	 * 
	 */
	public void stop(){
		if (status != STOPPED){
			status = STOPPED;
			finalTime = java.lang.System.currentTimeMillis();
		}
	}
	/**
	 * 
	 */
	public void reset(){
		initialTime = java.lang.System.currentTimeMillis();
		finalTime = initialTime;
	}
	
	/**
	 * 
	 */
	public void read(){
		if (status == STOPPED) timeChorometred = (finalTime -initialTime);
		else{
			timeChorometred = (java.lang.System.currentTimeMillis() - initialTime);
			//return timeChorometred;
		}
	}
	
	@Override
	public String toString(){
		return ""+timeChorometred+" ms";
		
	}
}

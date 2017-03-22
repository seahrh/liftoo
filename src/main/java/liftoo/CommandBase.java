package liftoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommandBase {
	private static final Logger log = LoggerFactory.getLogger(CommandBase.class);
	private Lift lift = null;
	private boolean isExternal = false;
	
	public void execute() {
		if (abort()) {
			return;
		}
		callReceiver();
	}
	
	abstract protected void callReceiver();
	
	public Lift lift() {
		return lift;
	}
	
	public void lift(Lift lift) {
		if (lift == null) {
			log.error("lift must not be null");
			throw new IllegalArgumentException();
		}
		this.lift = lift;
	}
	
	public boolean isExternal() {
		return isExternal;
	}
	
	public void isExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}
	
	private boolean abort() {
		if (isExternal && lift.isMaintenanceState()) {
			log.info("A lift does not accept external commands in MAINTENANCE state.");
			return true;
		}
		return false;
	}
	
}

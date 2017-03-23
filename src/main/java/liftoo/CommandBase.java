package liftoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommandBase {
	private static final Logger log = LoggerFactory.getLogger(CommandBase.class);
	private Lift lift = null;
	private long time = 0;
	private boolean isExternal = false;
	
	public CommandBase(Lift lift, long time, boolean isExternal) {
		this(lift, time);
		isExternal(isExternal);
	}
	
	public CommandBase(Lift lift, long time) {
		lift(lift);
		time(time);
	}

	public void execute() {
		if (isIllegalOperation()) {
			log.warn("Illegal operation: command aborted");
			return;
		}
		doIt();
	}

	abstract protected void doIt();

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

	public long time() {
		return time;
	}

	public void time(long time) {
		if (time < 0) {
			log.error("time must be greater than or equal to zero");
			throw new IllegalArgumentException();
		}
		this.time = time;
	}

	public boolean isExternal() {
		return isExternal;
	}

	public void isExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}

	private boolean isIllegalOperation() {
		if (isExternal && lift.isMaintenanceState()) {
			log.info("A lift does not accept external commands in MAINTENANCE state.");
			return true;
		}
		return false;
	}

}

package liftoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoToFloorCommand extends CommandBase {
	private static final Logger log = LoggerFactory.getLogger(GoToFloorCommand.class);
	private int floor = 1;

	public GoToFloorCommand(Lift lift, long time, boolean isExternal, int floor) {
		super(lift, time, isExternal);
		floor(floor);
	}

	public GoToFloorCommand(Lift lift, long time, int floor) {
		super(lift, time);
		floor(floor);
	}

	protected void doIt() {
		Lift lift = lift();
		int curr = lift.floor();
		if (floor == curr) {
			log.warn("Invalid GoToFloorCommand: Floor request is the same as current floor. Issue OpenDoorCommand instead.");
			return;
		}
		// Upper floor
		if (floor > curr) {
			lift.enqueueFloorUpward(curr);
			return;
		}
		// Lower floor
		lift.enqueueFloorDownward(curr);
	}

	public int floor() {
		return floor;
	}

	private void floor(int floor) {
		if (floor < 1) {
			log.error("floor must be greater than or equal to 1");
			throw new IllegalArgumentException();
		}
		this.floor = floor;
	}
}

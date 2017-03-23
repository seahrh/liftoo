package liftoo;

public class CloseDoorCommand extends CommandBase {

	public CloseDoorCommand(Lift lift, long time, boolean isExternal) {
		super(lift, time, isExternal);
	}
	
	public CloseDoorCommand(Lift lift, long time) {
		super(lift, time);
	}

	protected void doIt() {
		Lift lift = lift();
		if (lift.isDoorOpen()) {
			lift.closeDoor();
		}
	}
}

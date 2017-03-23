package liftoo;

public class OpenDoorCommand extends CommandBase {

	public OpenDoorCommand(Lift lift, long time, boolean isExternal) {
		super(lift, time, isExternal);
	}
	
	public OpenDoorCommand(Lift lift, long time) {
		super(lift, time);
	}

	protected void doIt() {
		Lift lift = lift();
		if (!lift.isDoorOpen()) {
			lift.openDoor();
		}
	}
}

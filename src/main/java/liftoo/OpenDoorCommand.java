package liftoo;

public class OpenDoorCommand extends CommandBase {

	public OpenDoorCommand(Lift lift) {
		lift(lift);
	}

	protected void callReceiver() {
		Lift lift = lift();
		if (!lift.isDoorOpen()) {
			lift.openDoor();
		}
	}
}

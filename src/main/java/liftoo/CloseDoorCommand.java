package liftoo;

public class CloseDoorCommand extends CommandBase {

	public CloseDoorCommand(Lift lift) {
		lift(lift);
	}

	protected void callReceiver() {
		Lift lift = lift();
		if (lift.isDoorOpen()) {
			lift.closeDoor();
		}
	}
}

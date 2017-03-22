package liftoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lift {
	private static final Logger log = LoggerFactory.getLogger(Lift.class);
	private int minFloor = 1;
	private int maxFloor = 1;
	private int floor = minFloor;
	private State state = State.STAND;
	private boolean isDoorOpen = false;
	private String id = null;

	private enum State {
		MAINTENANCE, STAND, UP, DOWN
	}

	protected Lift(String id, int minFloor, int maxFloor) {
		this(id, maxFloor);
		minFloor(minFloor);
		floor(minFloor);
	}
	
	protected Lift(String id, int maxFloor) {
		id(id);
		maxFloor(maxFloor);
	}

	protected int minFloor() {
		return minFloor;
	}

	protected void minFloor(int minFloor) {
		if (minFloor < 1) {
			log.error("minFloor must be greater than or equal to 1");
			throw new IllegalArgumentException();
		}
		if (minFloor > maxFloor) {
			log.error("minFloor must be less than or equal to maxFloor");
			throw new IllegalArgumentException();
		}
		this.minFloor = minFloor;
	}

	protected int maxFloor() {
		return maxFloor;
	}

	protected void maxFloor(int maxFloor) {
		if (maxFloor < 1) {
			log.error("maxFloor must be greater than or equal to 1");
			throw new IllegalArgumentException();
		}
		if (maxFloor < minFloor) {
			log.error("maxFloor must be greater than or equal to minFloor");
			throw new IllegalArgumentException();
		}
		this.maxFloor = maxFloor;
	}

	protected int floor() {
		return floor;
	}

	protected void floor(int floor) {
		if (floor < minFloor) {
			log.error(
					"current floor must not be less than minFloor\nfloor [{}]\nminFloor [{}]",
					floor, minFloor);
			throw new IllegalArgumentException();
		}
		if (floor > maxFloor) {
			log.error(
					"current floor must not be greater than maxFloor\nfloor [{}]\nmaxFloor [{}]",
					floor, maxFloor);
			throw new IllegalArgumentException();
		}
		this.floor = floor;
	}

	protected boolean isMaintenanceState() {
		return state.equals(State.MAINTENANCE);
	}
	
	protected void setMaintenanceState() {
		state = State.MAINTENANCE;
	}
	
	protected boolean isStandState() {
		return state.equals(State.STAND);
	}
	
	protected void setStandState() {
		state = State.STAND;
	}
	
	protected boolean isUpState() {
		return state.equals(State.UP);
	}
	
	protected void setUpState() {
		state = State.UP;
	}
	
	protected boolean isDownState() {
		return state.equals(State.DOWN);
	}
	
	protected void setDownState() {
		state = State.DOWN;
	}

	protected boolean isDoorOpen() {
		return isDoorOpen;
	}

	protected void openDoor() {
		isDoorOpen = true;
	}
	
	protected void closeDoor() {
		isDoorOpen = false;
	}

	protected String id() {
		return id;
	}
	
	private void id(String id) {
		if (id == null || id.isEmpty()) {
			log.error("id must not be null or empty string");
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

}

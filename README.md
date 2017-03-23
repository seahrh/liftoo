# Object-oriented design of a lift system

Uses the [Command pattern](https://en.wikipedia.org/wiki/Command_pattern), which allows for easy logging and flexible for client to issue commands whenever required. It has the following implementing subclasses:

- `GoToFloorCommand`
- `OpenDoorCommand`
- `CloseDoorCommand`

First there is a `Lift` class. It has a state (up, down, stand, maintenance) and a current floor.

A min-heap is used to store floor requests going up and a max-heap for floor requests going down.

The `LiftScheduler` contains the elevators and receives the requests from the floors. These are scheduled to all active elevators (not in maintenance).

The scheduling will be like:

- if available pick a standing elevator for this floor.
- else pick an elevator moving to this floor.
- else pick a standing elevator on an other floor.
- else pick the elevator with the lowest load.

Each elevator has a set of states.

- Maintenance: the elevator does not react to external signals (only to its own signals).
- Stand: the elevator is fixed on a floor. If it receives a call. And the elevator is on that floor, the doors open. If it is on another floor, it moves in that direction.
- Up: the elevator moves up. Each time it reaches a floor, it checks if it needs to stop. If so it stops and opens the doors. It waits for a certain amount of time and closes the door (unless someting is moving through them. Then it removes the floor from the request list and checks if there is another request. If so the elevator starts moving again. If not it enters the state stand.
- Down: like up but in reverse direction.

There are additional signals:

- alarm. The elevator stops. And if it is on a floor, the doors open, the request list is cleared, the requests moved back to the bank.
- door open. Opens the doors if an elevator is on a floor and not moving.
- door closes. Closed the door if they are open. 

Some elevators don't start at bottom/first_floor esp. in case of skyscrapers. `min_floor` & `max_floor` are two additional attributes for Elevator.

## References

1. [Solution based on this SO answer](http://stackoverflow.com/a/493350/519951)
2. [Interview question on CareerCup](https://www.careercup.com/question?id=1808669)
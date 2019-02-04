package commons;

import commons.eventScript.EventScript;
import java.io.*;
import java.util.*;

public class GameEvent implements Serializable {

	private Coords triggerPosition;
	private boolean playerTriggered = true;
	private Set<String> absentFlags = null;
	private Set<String> presentFlags = null;
	private List<EventScript> scripts = new ArrayList<>();
	private static Comparator<EventScript> comparator = (EventScript o1, EventScript o2) -> o2.getOrder() - o1.getOrder();

	public GameEvent(Coords triggerPosition, boolean playerTriggered, Set<String> absentFlags, Set<String> presentFlags, List<EventScript> scripts) {
		setTriggerPosition(triggerPosition);
		setPlayerTriggered(playerTriggered);
		setAbsentFlags(absentFlags);
		setPresentFlags(presentFlags);
		setScripts(scripts);
	}

	public void runEvent() {
		Collections.sort(scripts, comparator);
		scripts.forEach(e -> e.runScript());
	}

//	public boolean playerEligible() {
//		Engine.console.write("checking for elighibility", Color.ORANGE);
//		if (!Player.get().getPosition().equals(triggerPosition)) {
//			Engine.console.write(Engine.player.getPosition() + " " + triggerPosition, Color.ORANGE);
//			return false;
//		}
//		Engine.console.write("position match", Color.ORANGE);
//		
//		if (absentFlags == null) {
//			Engine.console.write("absent flags for event is null", Color.ORANGE);
//			if (!Engine.player.getFlags().isEmpty()) {
//				Engine.console.write("player flags is not empty", Color.ORANGE);
//				return false;
//			}
//		} else if (!Collections.disjoint(absentFlags, Player.get().getFlags())){
//			return false;
//		}
//		Engine.console.write("absent flags match", Color.ORANGE);
//		
//		if (presentFlags == null) {
//			Engine.console.write("event has null flags", Color.ORANGE);
//			if (!Player.get().getFlags().isEmpty()) {
//				Engine.console.write("player flags size is not null", Color.ORANGE);
//				return false;
//			}
//		} else if (!Player.get().getFlags().containsAll(presentFlags)) {
//			Engine.console.write("player flags contains all required flags for event", Color.ORANGE);
//			return false;
//		}
//		Engine.console.write("present flags match", Color.ORANGE);
//		return true;
//	}

	public List<EventScript> getEventPoints() {
		return scripts;
	}

	public Coords getTriggerPosition() {
		return triggerPosition;
	}

	@Override
	public String toString() {
		String trigger;
		if (playerTriggered) {
			trigger = "player triggered";
		} else {
			trigger = "engine triggered";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("GameEvent: " + trigger + " X: " + triggerPosition.getPosX() + " Y: " + triggerPosition.getPosY()).append("\n");
		if (absentFlags != null) {
			sb.append("no. abs. flags: " + absentFlags.size());
		} else {
			sb.append("no. abs. flags: 0");
		}

		if (presentFlags != null) {
			sb.append(" no. abs. flags: " + presentFlags.size());
		} else {
			sb.append("no. pres. flags: 0");
		}

		sb.append("number of eventPoints: " + scripts.size()).append("\n");

		return sb.toString();
	}

	public void setTriggerPosition(Coords triggerPosition) {
		this.triggerPosition = triggerPosition;
	}

	public void setAbsentFlags(Set<String> absentFlags) {
		this.absentFlags = absentFlags;
	}

	public void setPresentFlags(Set<String> presentFlags) {
		this.presentFlags = presentFlags;
	}

	public void setEventPoints(List<EventScript> eventPoints) {
		this.scripts = eventPoints;
	}

	public void setScripts(List<EventScript> scripts) {
		this.scripts = scripts;
	}

	public Set<String> getAbsentFlags() {
		return absentFlags;
	}

	public Set<String> getPresentFlags() {
		return presentFlags;
	}

	public boolean isPlayerTriggered() {
		return playerTriggered;
	}

	public void setPlayerTriggered(boolean playerTriggered) {
		this.playerTriggered = playerTriggered;
	}

}

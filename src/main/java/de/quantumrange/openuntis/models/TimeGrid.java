package de.quantumrange.openuntis.models;

import com.fasterxml.jackson.databind.JsonNode;
import de.quantumrange.openuntis.util.UntisParsUtil;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class TimeGrid {

	private final TimeBlock[] blocks;

	public TimeGrid(JsonNode node) {
		this.blocks = new TimeBlock[node.size()];

		for (int i = 0; i < this.blocks.length; i++) {
			this.blocks[i] = new TimeBlock(node.get(i));
		}
	}

	public static class TimeBlock {

		private final String name;
		private final LocalTime start, stop;

		public TimeBlock(JsonNode node) {
			this.name = node.get("name").asText();
			this.start = UntisParsUtil.parseTime(node.get("startTime"));
			this.stop = UntisParsUtil.parseTime(node.get("endTime"));
		}

		public String getName() {
			return name;
		}

		public LocalTime getStart() {
			return start;
		}

		public LocalTime getStop() {
			return stop;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			TimeBlock timeBlock = (TimeBlock) o;
			return Objects.equals(getName(), timeBlock.getName()) && Objects.equals(getStart(), timeBlock.getStart()) && Objects.equals(getStop(), timeBlock.getStop());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getName(), getStart(), getStop());
		}
	}

	public TimeBlock[] getBlocks() {
		return blocks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TimeGrid timeGrid = (TimeGrid) o;
		return Arrays.equals(blocks, timeGrid.blocks);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(blocks);
	}
}

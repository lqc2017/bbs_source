package grp3022.bbs.jo;

public class Percentage {
	private Integer index;
	private Float percent;

	public Percentage() {
	}

	public Percentage(Integer index, Float percent) {
		this.setIndex(index);
		this.setPercent(percent);
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}
}

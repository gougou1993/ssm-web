package cn.jxh.ssm.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList<T> implements Serializable {

	private static final long serialVersionUID = 6014528651409348097L;

	public PageList() {

	}

	public PageList(PageProperty pp, int allCount, List<T> list) {
		if (pp.getDraw() > 0) {
			this.draw = pp.getDraw();
		}
		this.recordsTotal = allCount;
		this.recordsFiltered = allCount;
		this.setData(list);
	}

	private int draw;

	private int recordsTotal;

	private int recordsFiltered;

	private List<T> data = new ArrayList<T>();

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

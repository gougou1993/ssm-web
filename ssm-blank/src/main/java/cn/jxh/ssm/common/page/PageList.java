package cn.jxh.ssm.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList<T> implements Serializable {

	private static final long serialVersionUID = 6014528651409348097L;

	public PageList() {

	}

	public PageList(PageProperty pp, int allCount, List<T> list) {
		if (pp.getNpage() > 0) {
			this.page = pp.getNpage();
		}
		if (pp.getNpagesize() > 0) {
			this.pageSize = pp.getNpagesize();
		}

		this.totalRecords = allCount;
		if (totalRecords % pageSize > 0) {
			this.totalPages = totalRecords / pageSize + 1;
		} else {
			this.totalPages = totalRecords / pageSize;
		}
		this.setRecords(list);
	}

	private int page = 1;

	private int totalRecords;

	private int totalPages;

	private int pageSize = 20;

	private int numbersPerBlock = 10;

	private List<T> records = new ArrayList<T>();

	private String pageStr;

	private String url;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1)
			page = 1;
		this.page = page;
	}

	public int getPageNumber() {
		int pageNumber = 0;
		if (totalRecords % pageSize == 0)
			pageNumber = totalRecords / pageSize;
		else
			pageNumber = 1 + totalRecords / pageSize;

		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * first row count of current page, start from 1
	 * 
	 * @return
	 */
	public int getFirstRow() {
		return (page - 1) * pageSize + 1;
	}

	/**
	 * last row count of current page
	 * 
	 * @return
	 */
	public int getLastRow() {
		return page == getPageNumber() ? getTotalRecords() : page * pageSize;
	}

	public int getPreviousPage() {
		return page > 1 ? page - 1 : page;
	}

	public int getNextPage() {
		return page < getPageNumber() ? page + 1 : page;
	}

	public int getBlocks() {
		if (this.getPageNumber() % this.numbersPerBlock == 0) {
			return this.getPageNumber() / this.numbersPerBlock;
		} else {
			return 1 + this.getPageNumber() / this.numbersPerBlock;
		}
	}

	public int getBlock() {
		if (this.getPage() % this.numbersPerBlock == 0) {
			return this.getPage() / this.numbersPerBlock;
		} else {
			return 1 + this.getPage() / this.numbersPerBlock;
		}
	}

	public int getNumbersPerBlock() {
		return numbersPerBlock;
	}

	public void setNumbersPerBlock(int numberPerBlock) {
		this.numbersPerBlock = numberPerBlock;
	}

	public int getPageOfPrevBlock() {
		if (this.getBlock() > 1) {
			return (this.getBlock() - 1) * this.getNumbersPerBlock();
		} else {
			return 1;
		}
	}

	public int getPageOfNextBlock() {
		if (this.getBlock() < this.getBlocks()) {
			return this.getBlock() * this.getNumbersPerBlock() + 1;
		} else {
			return this.getTotalRecords();
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalRecords >= 0) {
			sb.append("	<ul class=\"pagination pagination-sm no-margin pull-right\">\n");
			if (page == 1) {
				sb.append("	<li><a>第" + page + "/" + totalPages + "页共" + totalRecords + "条</a></li>\n");
				// sb.append("	<li><a style=\"padding:0px;\"><input style=\"width:50px;height:28px;border:0px;\" type=\"number\" value=\"\" id=\"toGoPage\" placeholder=\"页码\"/></a></li>\n");
				// sb.append("	<li><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			} else {
				sb.append("	<li><a>第" + page + "/" + totalPages + "页共" + totalRecords + "条</a></li>\n");
				// sb.append("	<li><a style=\"padding:0px;\"><input style=\"width:50px;height:28px;border:0px;\" type=\"number\" value=\"\" id=\"toGoPage\" placeholder=\"页码\"/></a></li>\n");
				// sb.append("	<li><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li><a onclick=\"searchPage('" + url + "?page=1','pageForm')\">首页</a></li>\n");
				sb.append("	<li><a onclick=\"searchPage('" + url + "?page=" + (page - 1) + "','pageForm')\">上页</a></li>\n");
			}
			int showTag = 5;// 分页标签显示数量
			int startTag = 1;
			if (page > showTag) {
				startTag = page - 1;
			}
			int endTag = startTag + showTag - 1;
			// for (int i = startTag; i <= totalPages && i <= endTag; i++) {
			// if (page == i)
			// sb.append("<li><a><font color='#808080'>" + i +
			// "</font></a></li>\n");
			// else
			// sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("
			// + i + ")\">" + i + "</a></li>\n");
			// }
			if (page == totalPages) {
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			} else {
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"searchPage('" + url + "?page=" + (page + 1) + "','pageForm')\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"searchPage('" + url + "?page=" + (totalPages) + "','pageForm')\">尾页</a></li>\n");
			}
			// sb.append("	<li><a>第" + page + "页</a></li>\n");
			// sb.append("	<li><a>共" + totalPages + "页</a></li>\n");

			sb.append("	<li><select name='pagesize' title='显示条数' style=\"height:30px;width:55px;float:left;\" onchange=\"searchPage('" + url + "','pageForm')\">\n");
			// sb.append("	<option value='" + pageSize + "'>" + pageSize +
			// "</option>\n");
			if (pageSize == 10) {
				sb.append("<option value=10 selected>10</option>");
			} else {
				sb.append("<option value=10>10</option>");
			}
			if (pageSize == 20) {
				sb.append("<option value=20 selected>20</option>");
			} else {
				sb.append("<option value=20>20</option>");
			}
			if (pageSize == 30) {
				sb.append("<option value=30 selected>30</option>");
			} else {
				sb.append("<option value=30>30</option>");
			}
			if (pageSize == 40) {
				sb.append("<option value=40 selected>40</option>");
			} else {
				sb.append("<option value=40>40</option>");
			}
			if (pageSize == 50) {
				sb.append("<option value=50 selected>50</option>");
			} else {
				sb.append("<option value=50>50</option>");
			}
			// sb.append("	<option value='10' &lt;c:if test='${"+pageSize+"==10}'&gt;  selected=\"selected\"  &lt;/c:if&gt;>10</option>\n");
			// sb.append("	<option value='20' &lt;c:if test='${"+pageSize+"==20}'&gt;  selected=\"selected\"  &lt;/c:if&gt;>20</option>\n");
			// sb.append("	<option value='30' &lt;c:if test='${"+pageSize+"==30}'&gt;  selected=\"selected\"  &lt;/c:if&gt;>30</option>\n");
			sb.append("	</select>\n");
			sb.append("	</li>\n");

			sb.append("</ul>\n");

			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function reloadPage(){");
			sb.append("searchPage('" + url + "','pageForm')");
			sb.append("}\n");
			sb.append("</script>\n");

			// sb.append("<script type=\"text/javascript\">\n");
			//
			// // 换页函数
			// sb.append("function nextPage(page){");
			// //sb.append(" top.jzts();");
			// sb.append("	if(true && document.forms[0]){\n");
			// sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			// sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField
			// ? "page" : "pageList.page") + "=\";}\n");
			// sb.append("		else{url += \"?" + (entityOrField ? "page" :
			// "pageList.page") + "=\";}\n");
			// sb.append("		url = url + page + \"&" + (entityOrField ?
			// "pageSize" : "pageList.pageSize") + "=" + pageSize + "\";\n");
			// sb.append("		document.forms[0].action = url;\n");
			// sb.append("		document.forms[0].submit();\n");
			// sb.append("	}else{\n");
			// sb.append("		var url = document.location+'';\n");
			// sb.append("		if(url.indexOf('?')>-1){\n");
			// sb.append("			if(url.indexOf('page')>-1){\n");
			// sb.append("				var reg = /page=\\d*/g;\n");
			// sb.append("				url = url.replace(reg,'page=');\n");
			// sb.append("			}else{\n");
			// sb.append("				url += \"&" + (entityOrField ? "page" :
			// "pageList.page") + "=\";\n");
			// sb.append("			}\n");
			// sb.append("		}else{url += \"?" + (entityOrField ? "page" :
			// "pageList.page") + "=\";}\n");
			// sb.append("		url = url + page + \"&" + (entityOrField ?
			// "pageSize" : "pageList.pageSize") + "=" + pageSize + "\";\n");
			// sb.append("		document.location = url;\n");
			// sb.append("	}\n");
			// sb.append("}\n");
			//
			// // 调整每页显示条数
			// sb.append("function changeCount(value){");
			// //sb.append(" top.jzts();");
			// sb.append("	if(true && document.forms[0]){\n");
			// sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			// sb.append("		if(url.indexOf('?')>-1){url += \"&" + (entityOrField
			// ? "page" : "pageList.page") + "=\";}\n");
			// sb.append("		else{url += \"?" + (entityOrField ? "page" :
			// "pageList.page") + "=\";}\n");
			// sb.append("		url = url + \"1&" + (entityOrField ? "pageSize" :
			// "pageList.pageSize") + "=\"+value;\n");
			// sb.append("		document.forms[0].action = url;\n");
			// sb.append("		document.forms[0].submit();\n");
			// sb.append("	}else{\n");
			// sb.append("		var url = document.location+'';\n");
			// sb.append("		if(url.indexOf('?')>-1){\n");
			// sb.append("			if(url.indexOf('page')>-1){\n");
			// sb.append("				var reg = /page=\\d*/g;\n");
			// sb.append("				url = url.replace(reg,'page=');\n");
			// sb.append("			}else{\n");
			// sb.append("				url += \"1&" + (entityOrField ? "page" :
			// "pageList.page") + "=\";\n");
			// sb.append("			}\n");
			// sb.append("		}else{url += \"?" + (entityOrField ? "page" :
			// "pageList.page") + "=\";}\n");
			// sb.append("		url = url + \"&" + (entityOrField ? "pageSize" :
			// "pageList.pageSize") + "=\"+value;\n");
			// sb.append("		document.location = url;\n");
			// sb.append("	}\n");
			// sb.append("}\n");
			//
			// // 跳转函数
			// sb.append("function toTZ(){");
			// sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			// sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			// sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			// sb.append("nextPage(toPaggeVlue);");
			// sb.append("}\n");
			// sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

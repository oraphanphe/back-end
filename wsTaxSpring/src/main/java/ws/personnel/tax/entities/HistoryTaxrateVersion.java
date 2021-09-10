package ws.personnel.tax.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HistoryTaxrateVersion implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "version", unique = true, nullable = false)
	private int version;
	
	@Column(name = "no", unique = true, nullable = false)
	private String no;

	public HistoryTaxrateVersion(){}
	
	public HistoryTaxrateVersion(int version, String no) {
		this.version = version;
		this.no = no;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}

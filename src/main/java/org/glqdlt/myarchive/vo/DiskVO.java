package org.glqdlt.myarchive.vo;

public class DiskVO {

	private String diskName;
	private Integer diskFreeSize;
	private Integer diskTotalSize;
	public String getDiskName() {
		return diskName;
	}
	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}
	public Integer getDiskFreeSize() {
		return diskFreeSize;
	}
	public void setDiskFreeSize(Integer diskFreeSize) {
		this.diskFreeSize = diskFreeSize;
	}
	public Integer getDiskTotalSize() {
		return diskTotalSize;
	}
	public void setDiskTotalSize(Integer diskTotalSize) {
		this.diskTotalSize = diskTotalSize;
	}
	public Integer getDiskUseSize() {
		return diskUseSize;
	}
	public void setDiskUseSize(Integer diskUseSize) {
		this.diskUseSize = diskUseSize;
	}
	private Integer diskUseSize;

}

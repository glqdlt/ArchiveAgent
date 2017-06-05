package org.glqdlt.myarchive.utill;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import org.glqdlt.myarchive.vo.DiskVO;

import com.sun.management.OperatingSystemMXBean;

public class OSMonitor {
	public List<DiskVO> getDiskSize() {

		List<DiskVO> list = new ArrayList<>();

		File[] roots = File.listRoots();

		for (File root : roots) {

			if (root.getTotalSpace() == 0) {
				continue;
			}

			DiskVO dvo = new DiskVO();
			dvo.setDiskName(root.getAbsolutePath());
			dvo.setDiskTotalSize((int) (root.getTotalSpace() / Math.pow(1024, 3)));
			dvo.setDiskUseSize((int) (root.getUsableSpace() / Math.pow(1024, 3)));
			dvo.setDiskFreeSize((int) (root.getFreeSpace() / Math.pow(1024, 3)));
			list.add(dvo);
		}

		return list;

	}

	public void getCpuUsable() {

		double load;

		while (true) {
			load = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad();

			if (load < 0.0) {
				continue;
			}

			System.out.println("CPU Usage : " + Math.round((load * 100.0)) + "%");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void getDiskSmartCtl(String diskNames) {

	}

}

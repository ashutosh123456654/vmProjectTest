package hpe.csa.helperUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import hpe.csa.model.VM;

public class Helper {
	
	public Timestamp getSqlDateFromToday(int daysFromNow) {
	
		int secsToadd=daysFromNow*24*60*60;
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.SECOND, secsToadd);
		Timestamp time=new Timestamp(cal.getTimeInMillis());

		return time;
	}
	
	public String allVMsToString(List<VM> vms) {
		String res="";
		for(VM v:vms) {
			res+=v.toString()+"\n";
		}
		return res;
	}

}

package pe.com.vendemas.weblogistica.util;

public class ErrorUtil {
	
	public static String formatError(Exception e) {
		String msg = "\n[ERROR][CLASS]["+e.getClass()+"]\n";
			   msg += "[CAUSE]["+e.getCause()+"]\n";
			   msg += "[MESSAGE]["+e.getMessage()+"]";
//			   msg += "[LOCALIZED]["+e.getLocalizedMessage()+"]";
//			   msg += "[STACK]["+e.getStackTrace()+"]";
//			   msg += "[SUPRESSED]["+e.getSuppressed()+"]";
		return msg;
	}
	
	public static String formatError(Exception e, String str) {
		String msg = "\n[ERROR][CLASS]["+e.getClass()+"]\n";
			   msg += "[CAUSE]["+e.getCause()+"]\n";
			   msg += "[MESSAGE]["+e.getMessage()+"]\n";
			   msg += "[DETAIL]["+str+"]";
//			   msg += "[LOCALIZED]["+e.getLocalizedMessage()+"]";
//			   msg += "[STACK]["+e.getStackTrace()+"]";
//			   msg += "[SUPRESSED]["+e.getSuppressed()+"]";
		return msg;
	}
	
}

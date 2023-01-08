package interpreter;

import java.util.HashMap;

import interpreter.bytecode.*;

public class Program {
	int index = 0;
	HashMap <Integer, ByteCode> codes = new HashMap<Integer, ByteCode>(); 
	public ByteCode getCode(int programCounter) {
		return codes.get(programCounter);
	}

	public void addCode(ByteCode code) {
		codes.put(index, code);
		index++;
	}
	
	public void resolveSymbolicAddresses() {
		int lines = codes.size();
		for (int i=0; i < lines; i++) {
			ByteCode code = codes.get(i);
		    if (code instanceof LabelCode) {
		    	((LabelCode)code).setLabelTarget(i);
		    	String label = code.getArgs().get(0);
		    	setTargets(i, label);
		    }
		}
	}
	private void setTargets(int pc, String label) {
		int lines = codes.size();
		for (int i=0; i < lines; i++) {
			ByteCode code = codes.get(i);
		    if (code instanceof GotoCode) {
		    	String goto_label = code.getArgs().get(0);
		    	if (goto_label.equals(label)) {
		    		((GotoCode)code).setBranchTarget(pc);
		    	}
		    }
		    else if (code instanceof FalsebranchCode) {
		    	String fb_label = code.getArgs().get(0);
		    	if (fb_label.equals(label)) {
		    		((FalsebranchCode)code).setBranchTarget(pc);
		    	}
		    }
		    else if (code instanceof CallCode) {
		    	String call_label = code.getArgs().get(0);
		    	if (call_label.equals(label)) {
		    		((CallCode)code).setBranchTarget(pc);
		    	}
		    }
		}
	}
	
}
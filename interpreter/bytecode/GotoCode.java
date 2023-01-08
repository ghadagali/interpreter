package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class GotoCode extends ByteCode {
	int branchTarget;
	public GotoCode() {
		
	}
	public GotoCode(List<String> params) {
		args = params;
	}

	@Override
	public void execute(VirtualMachine vm) {
		vm.setPc(branchTarget);
	}

	public int getBranchTarget() {
		return branchTarget;
	}
	
	public void setBranchTarget(int pc)	{
		branchTarget = pc;
	}
}

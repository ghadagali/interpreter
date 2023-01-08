package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class CallCode extends ByteCode {
	int branchTarget;
	public CallCode() {
	}
	public CallCode(List<String> params) {
		args = params;
	}

	@Override
	public void execute(VirtualMachine vm) {
		int store = vm.getPc();
		vm.pushReturnAddress(store);
		vm.setPc(branchTarget);
	}

	public int getBranchTarget() {
		return branchTarget;
	}
	
	public void setBranchTarget(int pc)	{
		branchTarget = pc;
	}

}

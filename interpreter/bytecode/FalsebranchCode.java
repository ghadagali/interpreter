package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class FalsebranchCode extends ByteCode {
	int branchTarget;
	public FalsebranchCode() {
		
	}
	public FalsebranchCode(List<String> params) {
		args = params;
	}

	@Override
	public void execute(VirtualMachine vm) {
		// pop top of rts and if value is 0 then branch else continue
		int check = vm.popRunStack();
		if (check == 0) {
			vm.setPc(branchTarget);
		}
	}

	public int getBranchTarget() {
		return branchTarget;
	}
	
	public void setBranchTarget(int pc)	{
		branchTarget = pc;
	}

}

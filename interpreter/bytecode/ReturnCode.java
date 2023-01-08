package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;
import interpreter.errors.StackUnderflowException;

public class ReturnCode extends ByteCode {
	private int branchTarget;
	public ReturnCode() {
	}
	public ReturnCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		try {
			vm.popFrame();
			branchTarget = vm.popReturnAddress();
			vm.setPc(branchTarget);
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}

	}
	
	public int getBranchTarget() {
		
		return branchTarget;
	}
	
	public void setBranchTarget(int pc)	{
		branchTarget = pc;
	}


}

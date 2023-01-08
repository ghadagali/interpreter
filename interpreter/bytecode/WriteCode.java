package interpreter.bytecode;

import interpreter.VirtualMachine;

public class WriteCode extends ByteCode {
	
	@Override
	public void execute(VirtualMachine vm) {
		int val = vm.peekRunStack();
		vm.write(val);
	}

}

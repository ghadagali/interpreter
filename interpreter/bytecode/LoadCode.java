package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class LoadCode extends ByteCode {
	public LoadCode() {
	}
	public LoadCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		int offset = Integer.parseInt(args.get(0));
		vm.loadRunStack(offset);
	}

}

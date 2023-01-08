package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class ArgsCode extends ByteCode {
	public ArgsCode() {
	}
	public ArgsCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		// get number of args and create new frame
		int offset = Integer.parseInt(args.get(0));
		vm.newFrame(offset);
	}
	
	
}

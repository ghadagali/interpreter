package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class PopCode extends ByteCode {
	public PopCode() {
	}
	public PopCode(List<String> params) {
		args = params;
	}

	@Override
	public void execute(VirtualMachine vm) {
		int count = Integer.parseInt(args.get(0));
		for (int j=0; j < count; j++) {
			vm.popRunStack();
		}
	}

}

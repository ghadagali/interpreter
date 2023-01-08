package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class StoreCode extends ByteCode {
	public StoreCode() {
	}
	public StoreCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		int offset = Integer.parseInt(args.get(0));
		vm.storeRunStack(offset);
	}

}

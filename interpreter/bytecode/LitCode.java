package interpreter.bytecode;

import java.util.List;
import interpreter.VirtualMachine;

public class LitCode extends ByteCode {
	
	public LitCode() {
	}
	public LitCode(List<String> params) {
		args = params;
	}

	@Override
	public void execute(VirtualMachine vm) {
			Integer i = Integer.parseInt(args.get(0));
			vm.pushRunStack(i);
	}

}

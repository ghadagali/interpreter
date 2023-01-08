package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class DbgCode extends ByteCode {
	public DbgCode() {
	}
	public DbgCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		int code = Integer.parseInt(args.get(0));
		if (code == 1) {
			vm.setOutputting(true);
		}
		else {
			vm.setOutputting(false);
		}
			
		
		
	}

}

package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class BopCode extends ByteCode {
	public BopCode() {
	}
	public BopCode(List<String> params) {
		args = params;
	}
	@Override
	public void execute(VirtualMachine vm) {
		int op1 = vm.popRunStack();
		int op2 = vm.popRunStack();
		String oper = args.get(0);
		int res = 0;
		switch (oper) {
			case "+" :
				res = op2 + op1;
				break;
			case "-":
				res = op2 - op1;
				break;
			case "*" :
				res = op2 * op1;
				break;
			case "/":
				res = op2 / op1;
				break;
			case "==" :
				if (op2 == op1) res = 1; else res = 0;
				break;
			case "!=":
				if (op2 != op1) res = 1; else res = 0;
				break;
			case "<" :
				if (op2 < op1) res = 1; else res = 0;
				break;
			case "<=":
				if (op2 <= op1) res = 1; else res = 0;
				break;
			case ">" :
				if (op2 > op1) res = 1; else res = 0;
				break;
			case ">=":
				if (op2 >= op1) res = 1; else res = 0;
				break;
			case "|" :
				res = op2 | op1;
				break;
			case "&":
				res = op2 & op1;
				break;
			default:
				break;
		}
	   vm.pushRunStack(res);
	}

}

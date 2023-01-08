package interpreter.bytecode;

import java.util.List;

import interpreter.VirtualMachine;

public class LabelCode extends ByteCode {
	private int labelTarget;
	String label;
	public LabelCode() {
	
	}
	public LabelCode(List<String> params) {
		args = params;
	}
	
	@Override
	public void execute(VirtualMachine vm) {
		label = args.get(0);
		labelTarget = vm.getPc();
	}
	public int getLabelTarget() {
		return labelTarget;
	}

	public String getLabel() {
		return label;
	}
	
	public void setLabelTarget(int labelTarget) {
		this.labelTarget = labelTarget;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}

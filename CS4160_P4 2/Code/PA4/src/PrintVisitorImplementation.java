
public class PrintVisitorImplementation implements Print_Visitor {

	@Override
	public String print(Goal goal) {
		// TODO Auto-generated method stub
		return "Goal "+goal.id+" "+goal.content;
	}

	@Override
	public String print(Assumption assumption) {
		// TODO Auto-generated method stub
		System.out.println(assumption.id);
		System.out.println(assumption.desc);
		return null;
	}

	@Override
	public String print(Justification justification) {
		// TODO Auto-generated method stub
		System.out.println(justification.id);
		System.out.println(justification.desc);
		return null;
	}

	@Override
	public String print(Solution solution) {
		// TODO Auto-generated method stub
		return "Solution "+solution.id+" "+solution.content;
	}

	@Override
	public String print(Context context) {
		// TODO Auto-generated method stub
		return "Context "+context.id+" "+context.content;
	}

	@Override
	public String print(Strategy strategy) {
		// TODO Auto-generated method stub
		return "Strategy "+strategy.id+" "+strategy.content;
	}

	

}

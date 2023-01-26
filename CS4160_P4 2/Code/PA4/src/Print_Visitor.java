public interface Print_Visitor {
    String print(Goal goal);
    String print(Assumption assumption);
    String print(Justification justification);
    String print(Solution solution);
    String print(Context context);
    String print(Strategy strategy);
    
}

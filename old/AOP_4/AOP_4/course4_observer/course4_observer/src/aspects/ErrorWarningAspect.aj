package aspects;

public aspect ErrorWarningAspect {
	pointcut noCallToRepoFromGUI():call(* contest.repository..*.*(..))&& within(contest.gui..*);
	declare error: noCallToRepoFromGUI():"You should not call/use the repositories from GUI";
	declare warning: noCallToRepoFromGUI():"You should not call/use the repositories from GUI";

}

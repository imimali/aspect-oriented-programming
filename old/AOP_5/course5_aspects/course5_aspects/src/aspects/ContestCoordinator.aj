package aspects;

public aspect ContestCoordinator {
	declare precedence: Precon*, ContestObserver, SystemMonitoring*;
}

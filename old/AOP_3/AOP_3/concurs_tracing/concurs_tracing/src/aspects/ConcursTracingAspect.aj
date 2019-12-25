package aspects;

public aspect ConcursTracingAspect {
	pointcut tracing():execution(public * concurs..*.*(..))&& !(within(ConcursTracingAspect)||within(concurs.gui.*));
	private int indentation;
	before(): tracing(){
		indentation++;
		printIndentation();
		System.out.println("Entering method: "+thisJoinPointStaticPart.getSignature());
	}
	
	after():tracing(){
		printIndentation();
		System.out.println("Exiting method: "+thisJoinPointStaticPart.getSignature());
		indentation--;
		
	}
	
	void printIndentation(){		
		for(int i=0;i<indentation;i++){
			System.out.print("  ");
		}
	}

}

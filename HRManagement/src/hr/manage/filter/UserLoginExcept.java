package hr.manage.filter;

public class UserLoginExcept extends RuntimeException{


public UserLoginExcept()
   {
	   super();
   }
   
   public UserLoginExcept(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public UserLoginExcept(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserLoginExcept(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
   
}

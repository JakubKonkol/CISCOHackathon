public abstract class HttpServlet extends GenericServlet {
	
...
private com.appd.hackathon.instrumentation.interceptors.ServletMethodInterceptor interceptorClassVariable_123;
...

    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
    	// Content of insertBefore
		{
			this.interceptorClassVariable_123 = new %s();
			this.interceptorClassVariable_123.onMethodBegin(this, "javax.servlet.http.HttpServlet", "service", paramValues)
		}

		... // Originating service() method body

		// Content of insertAfter
		{
			this.interceptorClassVariable_123.onMethodEnd(this, "javax.servlet.http.HttpServlet", "service", paramValues, returnValue)
		}

    }
...
}

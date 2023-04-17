Source: https://github.com/ExeterBScDTS/simple-servlet

#This project contains very basic servlet example without complicated code.

### This projects helps one understand the basic working of *servlets*, *sessions*, *lifecycle of servlets* and how *request*, *session* and *servlet context* scopes can be used to share data.  
The hyperlinks in the index.html(http://{IP_ADDR}:8080/ServletSample/) are self explanatory

 *Note: Check console after each operation to understand the flow and also go through the code and comments in the code for better understanding. In Servlets, we write html code inside java code.* 

###### Operations

1)**GET**,**POST** requests to servlet.  
2)**Session** creation.  
3)Testing session.  
4)Data sharing in **Request**, **Session** and **Servlet Context** scopes.  
5)Testing the data stored in session and servlet context scopes.  
6)Invalidating the session.  

 *Note: Use multiple browsers(clients) to test data in sessions, servlet context. This will help the user to understand better*  

## Points to be noted
##### Servlet lifecycle
1) Loading of servlet class.  
2) Instantiation(no-arg constructor).  
3) Initialization(init method).  
4) Invoke service() method in a new thread of execution(which in turn invoke doXXX methods).  
5) Destroy(destroy method)  

*Note: Since servlet is a singleton, from 2nd request first 3 steps will not be performed. From 2nd request service() method is invoked in a new thread of execution.*
 
###### Session is one per client(browser) across n requests
###### Servlet Context is one per web application  

*Note: This project needs to be refactored to use best practices which will be done later. As of now this code should be used only for understanding purposes.*

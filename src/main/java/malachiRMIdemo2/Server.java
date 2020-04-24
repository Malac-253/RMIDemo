package malachiRMIdemo2;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Step 2 : Implement the server
 * 
A "server" class, in this context, is the class which has a main method that creates an instance 
of the remote object implementation, exports the remote object, and then binds that instance to 
a name in a Java RMI registry. The class that contains this main method could be the implementation 
class itself, or another class entirely.
*
In this example, the main method for the server is defined in the class Server which also implements 
the remote interface Hello. The server's main method does the following:
*
 - Create and export a remote object
 - Register the remote object with a Java RMI registry
*
*
The implementation class Server implements the remote interface Hello, providing an implementation for 
the remote method sayHello. The method sayHello does not need to declare that it throws any exception 
because the method implementation itself does not throw RemoteException nor does it throw any other 
checked exceptions.

Note: A class can define methods not specified in the remote interface, but those methods can only be 
invoked within the virtual machine running the service and cannot be invoked remotely.
*
Here is the source code for the class Server. Descriptions for writing this server class follow the source code:
 */


//client can be away
// /c/Program\ Files\ \(x86\)/Java/jre1.8.0_251/bin/rmiregistry.exe

public class Server implements Hello {
	        
	    public Server() {}

	    public String sayHello() {
	        return "Hello, world!";
	    }
	    public String sayfive(int test) {
	        return "Hello, number!" + (test * 5) ;
	    }   
	    public static void main(String args[]) {
	        
	        try {
	        	
	        	/**
	        	 * Step 2-1: Create and export a remote object
				The main method of the server needs to create the remote object that provides the service. 
				Additionally, the remote object must be exported to the Java RMI runtime so that it may 
				receive incoming remote calls. This can be done as follows:
	        	*
	        	The static method UnicastRemoteObject.exportObject exports the supplied remote object to 
	        	receive incoming remote method invocations on an anonymous TCP port and returns the stub 
	        	for the remote object to pass to clients. As a result of the exportObject call, the 
	        	runtime may begin to listen on a new server socket or may use a shared server socket to 
	        	accept incoming remote calls for the remote object. The returned stub implements the same 
	        	set of remote interfaces as the remote object's class and contains the host name and port 
	        	over which the remote object can be contacted.
	        	*
				Note: As of the J2SE 5.0 release, stub classes for remote objects no longer need to be 
				pregenerated using the rmic stub compiler, unless the remote object needs to support 
				clients running in pre-5.0 VMs. If your application needs to support such clients, you will 
				need to generate stub classes for the remote objects used in the application and deploy 
				those stub classes for clients to download. For details on how to generate stub classes, 
				see the tools documentation for rmic [Solaris, Windows]. For details on how to deploy your 
				application along with pregenerated stub classes, see the codebase tutorial.
	        	 */
	        	
	            Server obj = new Server();
	            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
	            
	            /**
	        	 * Step 2-2: Register the remote object with a Java RMI registry
	            For a caller (client, peer, or applet) to be able to invoke a method on a remote object, 
	            that caller must first obtain a stub for the remote object. For bootstrapping, Java RMI 
	            provides a registry API for applications to bind a name to a remote object's stub and for 
	            clients to look up remote objects by name in order to obtain their stubs.
				*
				A Java RMI registry is a simplified name service that allows clients to get a reference 
				(a stub) to a remote object. In general, a registry is used (if at all) only to locate the 
				first remote object a client needs to use. Then, typically, that first object would in turn 
				provide application-specific support for finding other objects. For example, the reference 
				can be obtained as a parameter to, or a return value from, another remote method call. For 
				a discussion on how this works, please take a look at Applying the Factory Pattern to Java RMI.
				*
				Once a remote object is registered on the server, callers can look up the object by name, 
				obtain a remote object reference, and then invoke remote methods on the object.
				*
				The following code in the server obtains a stub for a registry on the local host and default 
				registry port and then uses the registry stub to bind the name "Hello" to the remote object's 
				stub in that registry:
				*
				The static method LocateRegistry.getRegistry that takes no arguments returns a stub that 
				implements the remote interface java.rmi.registry.Registry and sends invocations to the 
				registry on server's local host on the default registry port of 1099. The bind method is 
				then invoked on the registry stub in order to bind the remote object's stub to the name 
				"Hello" in the registry.
				*
				Note: The call to LocateRegistry.getRegistry simply returns an appropriate stub for a registry. 
				The call does not check to see if a registry is actually running. If no registry is running 
				on TCP port 1099 of the local host when the bind method is invoked, the server will fail 
				with a RemoteException.
				*
	            */
	            
	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            //name
	            registry.bind("Hello", stub);
	            

	            System.err.println("Server ready");
	        } catch (Exception e) {
	            System.err.println("Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	    }
	}
	


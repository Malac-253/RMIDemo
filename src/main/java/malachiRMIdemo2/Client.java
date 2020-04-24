package malachiRMIdemo2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	/**
	 * Step 3 : Implement the client
	 * 
	The client program obtains a stub for the registry on the server's host, looks up the remote
	object's stub by name in the registry, and then invokes the sayHello method on the remote 
	object using the stub.
	*
	Here is the source code for the client:
	 *
	This client first obtains the stub for the registry by invoking the static 
	LocateRegistry.getRegistry method with the hostname specified on the command line. 
	If no hostname is specified, then null is used as the hostname indicating that the local host 
	address should be used.
	*
	Next, the client invokes the remote method lookup on the registry stub to obtain the stub 
	for the remote object from the server's registry.
	*
	Finally, the client invokes the sayHello method on the remote object's stub, which causes 
	the following actions to happen:
	*
	 -- The client-side runtime opens a connection to the server using the host and port information 
	in the remote object's stub and then serializes the call data.
	*
	 -- The server-side runtime accepts the incoming call, dispatches the call to the remote object, 
	 and serializes the result (the reply string "Hello, world!") to the client.
	*
	 -- The client-side runtime receives, deserializes, and returns the result to the caller.
	*
	The response message returned from the remote invocation on the remote object is 
	then printed to System.out.
	 */ 
	
    private Client() {}

    public static void main(String[] args) {
    	/**
    	 * 1:Find where the RMIregistry.exe was on the system
    	 * 2:Run RMIregistry.exe from directory that your class files are in with the absoluta path
    	 * 3:Run server
    	 * 4:Run client
    	 * 
    	 */
    	
    	
    	
    	
    	
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            //Registry registry = LocateRegistry.createRegistry(1099); for testing use
            //start server
            Hello stub = (Hello) registry.lookup("Hello");
            
            
            String response = stub.sayHello();
            String response2 = stub.sayfive(2);
            
            System.out.println("response: " + response);
            System.out.println("response 2: " + response2);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
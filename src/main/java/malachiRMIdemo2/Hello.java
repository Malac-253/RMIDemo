package malachiRMIdemo2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Step 1 : Define the remote interface
 * 
A remote object is an instance of a class that implements a remote interface. A remote interface 
extends the interface java.rmi.Remote and declares a set of remote methods. Each remote method 
must declare java.rmi.RemoteException (or a superclass of RemoteException) in its throws clause, 
in addition to any application-specific exceptions.
 *
Here is the interface definition for the remote interface used in this example, 
example.hello.Hello. It declares just one method, sayHello, which returns a string to the caller:
 *
Remote method invocations can fail in many additional ways compared to local method invocations 
(such as network-related communication problems and server problems), and remote methods will 
report such failures by throwing a java.rmi.RemoteException.
 */

public interface Hello extends Remote {
    String sayHello() throws RemoteException;
    String sayfive(int test) throws RemoteException;
}
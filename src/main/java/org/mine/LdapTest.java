package org.mine;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * Created by madhan.reddy on 3/14/2017.
 */
public class LdapTest {
  public static void main(String[] args) {
    String url = "ldap://wfm-cluster-1-1.openstacklocal:33389";
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, url);
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=people");
    env.put(Context.SECURITY_CREDENTIALS, "admin-password");
    try {
      DirContext ctx = new InitialDirContext(env);
      System.out.println("connected");
      System.out.println(ctx.getEnvironment());

      // do something useful with the context...

      ctx.close();

    } catch (AuthenticationNotSupportedException ex) {
      System.out.println("The authentication is not supported by the server");
    } catch (AuthenticationException ex) {
      System.out.println("incorrect password or username");
    } catch (NamingException ex) {
      System.out.println("error when trying to create the context");
    }
    System.out.println("Done" );

  }
}

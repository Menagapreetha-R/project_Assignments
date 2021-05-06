package com.hcl.traings.UserResource;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserResourceTest extends TestCase {
	
    public UserResourceTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( UserResourceTest.class );
    }

   
    public void testApp()
    {
        assertTrue( true );
    }

}

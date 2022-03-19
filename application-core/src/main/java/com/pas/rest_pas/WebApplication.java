package com.pas.rest_pas;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;


@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/")
public class WebApplication extends Application {
}

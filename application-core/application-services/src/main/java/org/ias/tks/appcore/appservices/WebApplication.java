package org.ias.tks.appcore.appservices;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/")
public class WebApplication extends Application {
}




import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.client.lib.CloudApplication;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudInfo;

public class MarkusDemo {

	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		CloudFoundryClient client = new CloudFoundryClient("foo@bar.com", "password", "http://217.160.94.108:9022");
		CloudInfo info = client.getCloudInfo();
		String name = info.getName();
		System.out.println("CLOUD INFO");
		System.out.println("Name : "+name);
		String description = info.getDescription();
		System.out.println("Description : "+description);
		System.out.println();

		client.login();

		showApps(client);

		System.out.println("Create a new application.");
		List<String> urls = new ArrayList<String>();
		urls.add("otto.vcap.me");
		client.createApplication("ottos-app", "sinatra", 32,urls ,new ArrayList<String>());
		showApps(client);

		// upload application

		// start application

		System.out.println("Delete an application.");
		client.deleteApplication("ottos-app");
		showApps(client);

		client.logout();

	}

	private static void showApps(CloudFoundryClient client) {
		System.out.println("CLOUD APPLICATIONS");
		List<CloudApplication> apps = client.getApplications();
		for(CloudApplication app : apps)
			System.out.println("Application : "+app.getName()+" | State : "+app.getState() + " | Instances: "+app.getInstances());
		System.out.println();
	}

}

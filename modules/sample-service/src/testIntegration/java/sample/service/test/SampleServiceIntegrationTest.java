package sample.service.test;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.liferay.arquillian.containter.remote.enricher.Inject;
import com.liferay.portal.kernel.exception.PortalException;

import sample.service.SampleService;

@RunWith(Arquillian.class)
public class SampleServiceIntegrationTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testAdd() throws IOException, PortalException {
		final long result = _sampleService.add(1, 3);

		Assert.assertEquals(4, result);
	}

	@Inject
	private SampleService _sampleService;

}